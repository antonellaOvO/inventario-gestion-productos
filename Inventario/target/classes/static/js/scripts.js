document.addEventListener("DOMContentLoaded", function () {
  const productTable = document.querySelector("#productTable tbody");

  // URL de tu backend para categorías, proveedores e ivas
  const API_BASE_URL = "http://localhost:8080";
  const categoriaUrl = `${API_BASE_URL}/api/categorias`;
  const proveedorUrl = `${API_BASE_URL}/api/proveedores`;
  const productoUrl = `${API_BASE_URL}/api/productos`;

  // Elementos del formulario para las categorías, subcategorías, proveedores y ivas
  
  const categoriaSelect = document.getElementById("categoria");
  const subcategoriaSelect = document.getElementById("subcategoria");
  const proveedorSelect = document.getElementById("provider");
  const ivaSelect = document.getElementById("iva");

  let idCategoria = null;

  // Cargar los productos en la tabla de index.html
  if (productTable) {
    crearTablaIndex();

    // Lógica para el botón "Ver detalles"
    productTable.addEventListener("click", function (e) {
      if (e.target.classList.contains("view-btn")) {
        const row = e.target.closest("tr");
        const idProducto = row.children[0].textContent;
        window.location.href = `../html/view-producto.html?idProducto=${idProducto}`;
      }

      // Lógica para el botón "Dar de baja"
      if (e.target.classList.contains("deactivate-btn")) {
        const row = e.target.closest("tr");
        const idProducto = row.children[0].textContent;
        const confirmModal = new bootstrap.Modal(
          document.getElementById("confirmModal")
        );

        confirmModal.show();

        document.getElementById("confirmDeactivation").onclick = function () {
          darBajaProducto(idProducto);
          row.classList.add("table-secondary");
          row.querySelector(".deactivate-btn").disabled = true;
          confirmModal.hide();

          // Mostrar modal de éxito
          const successDeactivationModal = new bootstrap.Modal(
            document.getElementById("deactivationSuccessModal")
          );
          successDeactivationModal.show();
        };
      }
    });

    // Lógica para manejar el botón "Modificar" en index.html
    productTable.addEventListener("click", function (e) {
      if (e.target.classList.contains("modify-btn")) {
        const row = e.target.closest("tr");
        const idProducto = row.children[0].textContent;
        window.location.href = `../html/edit-product.html?idProducto=${idProducto}`;
      }
    });
  }

  // Cargar las opciones de categorías en el formulario de add-producto.html
  if(categoriaSelect){
      loadCategoriasFormularioAddProducto();
  }
  
  // Cargar las opciones de categorías en el filtro de index.html
  loadCategoriasIndex();

  // Event listener para cuando se selecciona una categoría en el formulario de add-producto.html
  if (categoriaSelect) {
    categoriaSelect.addEventListener("change", function () {
    idCategoria = categoriaSelect.value;

    if (idCategoria) {
      const subcategoriaUrl = "http://localhost:8080/api/subcategorias";
      const ivaUrl = `http://localhost:8080/api/ivas/categoria/${idCategoria}`;

      // Limpiar las opciones de subcategorías anteriores
      subcategoriaSelect.innerHTML = "<option value=''>Seleccione una subcategoría</option>";
      loadSubcategoriasPorCategoriaEscogida(idCategoria, subcategoriaUrl);

      // Limpiar las opciones de ivas anteriores
      ivaSelect.innerHTML = "<option value=''>Seleccion un tipo de IVA</option>";
      loadIvasPorCategoriaEscogida(ivaUrl);
    }
  });
  
    }

    // Cargar las opciones de proveedores en el formulario de add-producto.html
    if(proveedorSelect){
      loadProveedores();  
    }
    
    // Cargar las opciones de proveedores en el filtro de index.html
    loadProveedoresIndex(); 

  // Lógica para manejar el envío del formulario para agregar productos
  const addProductForm = document.getElementById("addProductForm");
  if (addProductForm) {
    addProductForm.addEventListener("submit", function (e) {
      e.preventDefault();

      // Recoger valores del formulario
      const product = document.getElementById("product").value;
      const idSubcategoria = subcategoriaSelect.value;
      const description = document.getElementById("description").value;
      const stock = parseInt(document.getElementById("stock").value, 10);
      const unitPrice = parseFloat(document.getElementById("unitPrice").value);
      const idIva = ivaSelect.value;
      const provider = document.getElementById("provider").value;
      const fechaAdquisicion = document.getElementById("acquisitionDate").value;
      const fechaCaducidad = document.getElementById("expirationDate").value;

      // Validaciones
      // Elementos para mostrar mensajes de error
        let errorMessageAdquisicion = document.getElementById("error-fechaAdquisicion");
        let errorMessageCaducidad = document.getElementById("error-fechaCaducidad");

     // Limpiar mensajes de error previos
        errorMessageAdquisicion.textContent = "";
        errorMessageCaducidad.textContent = "";

    // Validaciones
        if (!fechaAdquisicion) {
          errorMessageAdquisicion.textContent = "Por favor ingresa una fecha de adquisición válida.";
          return;
        }

        if (!fechaCaducidad) {
          errorMessageCaducidad.textContent = "Por favor ingresa una fecha de caducidad válida.";
          return;
        }
      
      // Convertir fechas de cadena a objetos Date para comparación
        const fechaAdquisicionDate = new Date(fechaAdquisicion);
        const fechaCaducidadDate = new Date(fechaCaducidad);

        if (fechaCaducidadDate <= fechaAdquisicionDate) {
          errorMessageCaducidad.textContent = "La fecha de caducidad debe ser posterior a la fecha de adquisición.";
          return;
        }

      // Enviar el producto al backend
      fetch("http://localhost:8080/api/productos", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          nombreProducto: product,
          subcategoria: { idSubcategoria: idSubcategoria },
          iva: { idIva: idIva },
          proveedor: { idProveedor: provider },
          descripcion: description,
          stock: stock,
          precioUnidadSinIva: unitPrice.toFixed(2),
          fechaAdquisicion: fechaAdquisicion,
          fechaCaducidad: fechaCaducidad,
        }),
      })
        .then((response) => {
          if (response.ok) {
            const modalElement = document.getElementById("saveModal");
            const confirmModal = new bootstrap.Modal(modalElement, {
              backdrop: 'static',
              keyboard: false
            });
            confirmModal.show();
          } else {
            alert("Hubo un problema al agregar el producto.");
          }
        })
        .catch((error) => console.error("Error al agregar producto:", error));
    });
  }

  // Función para obtener y mostrar productos en la tabla de index.html
  function crearTablaIndex() {
    fetch(productoUrl)
      .then((response) => response.json())
      .then((data) => {
        const productTableBody = document.getElementById("productTable").querySelector("tbody");
        productTableBody.innerHTML = "";
        data.forEach((producto) => {
          const newRow = document.createElement("tr");
          
          // Aplicar clase "table-secondary" si el producto no está activo
            if (!producto.activo) {
              newRow.classList.add("table-secondary");
            }
          
          // Generar el contenido de la fila
          newRow.innerHTML = `
            <td class="text-center align-middle" id="producto-${producto.idProducto}">${producto.idProducto}</td>
            <td class="text-center align-middle">${producto.nombreProducto}</td>
            <td class="text-center align-middle">${producto.descripcion}</td>
            <td class="text-center align-middle">${producto.stock}</td>
            <td class="text-center align-middle">${producto.precioUnidadSinIva}€</td>
            <td class="text-center align-middle">${producto.precioTotalConIva}€</td>
            <td class="text-center align-middle">${producto.proveedor.nombreProveedor}</td>
            <td>
              <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                <button class="btn modify-btn">Modificar</button>
                <button class="btn view-btn">Ver detalles</button>
                <button class="btn deactivate-btn" ${!producto.activo ? "disabled" : ""}>Dar de baja</button>
              </div>
            </td>
          `;
          productTableBody.appendChild(newRow);
        });
      });
  }

  // Función para cargar las opciones de categorías en el formulario de añadir productos (add-product.html)
  function loadCategoriasFormularioAddProducto() {
    fetch(categoriaUrl)
      .then((response) => response.json())
      .then((data) => {
        data.forEach((categoria) => {
          const option = document.createElement("option");
          option.value = categoria.idCategoria;
          option.text = categoria.nombre;
          
          categoriaSelect.appendChild(option);
          
        });
      })
      .catch((error) => console.error("Error cargando categorías:", error));
  }
  
  // Función para cargar las opciones de categorías en el filtro de index.html
  async function loadCategoriasIndex() {
      const categoriaSelectFilter = document.getElementById("filterCategory");
    fetch(categoriaUrl)
      .then((response) => response.json())
      .then((data) => {
        data.forEach((categoria) => {
          const option = document.createElement("option");
          option.value = categoria.idCategoria;
          option.text = categoria.nombre;
          categoriaSelectFilter.appendChild(option);
        });
      })
      .catch((error) => console.error("Error cargando categorías en index:", error));
  }
  
  

  // Cargar las opciones de subcategorías para la categoría seleccionada
  function loadSubcategoriasPorCategoriaEscogida(idCategoria, subcategoriaUrl) {
    fetch(subcategoriaUrl)
      .then((response) => response.json())
      .then((data) => {
        data.forEach((subcategoria) => {
          if (subcategoria.categoria && subcategoria.categoria.idCategoria === parseInt(idCategoria)) {
            const option = document.createElement("option");
            option.value = subcategoria.idSubcategoria;
            option.text = subcategoria.nombreSubcategoria;
            subcategoriaSelect.appendChild(option);
          }
        });
      })
      .catch((error) => console.error("Error cargando subcategorías:", error));
  }

  // Cargar las opciones de IVAs por la categoría escogida
  function loadIvasPorCategoriaEscogida(ivaUrl) {
    fetch(ivaUrl)
      .then((response) => response.json())
      .then((data) => {
        data.forEach((iva) => {
          const option = document.createElement("option");
          option.value = iva.idIva;
          option.text = iva.tipoIva;
          ivaSelect.appendChild(option);
        });
      })
      .catch((error) => console.error("Error cargando ivas:", error));
  }
  
  

  // Cargar las opciones de proveedores en add-product.html
  function loadProveedores() {
    fetch(proveedorUrl)
      .then((response) => response.json())
      .then((data) => {
        data.forEach((proveedor) => {
          const option = document.createElement("option");
          option.value = proveedor.idProveedor;
          option.text = proveedor.nombreProveedor;
          proveedorSelect.appendChild(option);
          
        });
      })
      .catch((error) => console.error("Error cargando proveedores:", error));
  }
  
  // Función para cargar las opciones de proveedores en el filtro de index.html
  function loadProveedoresIndex() {
    const proveedorSelectFilter = document.getElementById("filterProvider");
    
    fetch(proveedorUrl)
      .then((response) => response.json())
      .then((data) => {
        data.forEach((proveedor) => {
          const option = document.createElement("option");
          option.value = proveedor.idProveedor;
          option.text = proveedor.nombreProveedor;
          proveedorSelectFilter.appendChild(option);
        });
      })
      .catch((error) => console.error("Error cargando proveedores en index:", error));
  }

  async function darBajaProducto(idProducto) {
    const baja = { activo: false };
    try {
      const response = await fetch(`http://localhost:8080/api/productos/${idProducto}`, {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(baja),
      });

      if (response.ok) {
        console.log("Producto dado de baja correctamente.");
      } else {
        alert("Error al dar de baja el producto");
      }
    } catch (error) {
      console.error("Error al dar de bajar el producto:", error);
    }
  }
  
  
  


});
