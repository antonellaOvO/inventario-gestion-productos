document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("form");

  // Obtener el ID del producto desde los parámetros de la URL
  const urlParams = new URLSearchParams(window.location.search);
  const idProducto = urlParams.get("idProducto");

  if (idProducto) {
    // Cargar los detalles del producto en el formulario al inicio
    cargarDetallesProductoId(idProducto);
  }

  // Evento submit del formulario
  form.addEventListener("submit", async (event) => {
    event.preventDefault(); // Prevenir el envío estándar
    
    // Validaciones
        const fechaAdquisicion = document.getElementById("fechaAdquisicion").value;
        const fechaCaducidad = document.getElementById("fechaCaducidad").value;
        
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
    
    if (idProducto) {
      await actualizarProducto(idProducto); // Llamar a la función para actualizar el producto
       
      // Lógica para mostrar el modal de éxito después de guardar
      const modalElement = document.getElementById("successModal");
      const confirmModal = new bootstrap.Modal(modalElement, {
        backdrop: 'static',
        keyboard: false
      });
      
       confirmModal.show(); // Mostrar el modal manualmente
      
    }
  });
});

// Función para cargar los detalles del producto en el formulario
async function cargarDetallesProductoId(idProducto) {
  try {
    const response = await fetch(`http://localhost:8080/api/productos/${idProducto}`);
    if (!response.ok) throw new Error("Producto no encontrado");

    const data = await response.json();
    console.log(data);
    
    // Cargar las opciones dinámicas para Categoría, Proveedor y Subcategoría
    await cargarOpcionesCategorias();  // Cargar las categorías
    await cargarOpcionesProveedores(); // Cargar los proveedores

    // Asignar los valores del producto a los campos correspondientes
    document.getElementById("nombreProducto").value = data.nombreProducto;
    document.getElementById("categoria").value = data.subcategoria.categoria.idCategoria || ""; 
    document.getElementById("subcategoria").value = data.subcategoria.idSubcategoria || ""; 
    document.getElementById("descripcion").value = data.descripcion;
    document.getElementById("stock").value = data.stock;
    document.getElementById("precioUnidad").value = data.precioUnidadSinIva;
    document.getElementById("proveedor").value = data.proveedor.idProveedor || ""; 
    document.getElementById("telefonoProveedor").value = data.proveedor.telefono;
    document.getElementById("emailProveedor").value = data.proveedor.email;
    document.getElementById("activo").checked = data.activo;
    document.getElementById("fechaAdquisicion").value = data.fechaAdquisicion;
    document.getElementById("fechaCaducidad").value = data.fechaCaducidad;

    // Después de cargar las opciones, cargar las subcategorías correctamente
    await cargarOpcionesSubcategorias(data.subcategoria.categoria.idCategoria);  // Recargar subcategorías con la categoría seleccionada
    // Seleccionar la subcategoría correcta si ya existe el valor
    if (data.subcategoria.idSubcategoria) {
      document.getElementById("subcategoria").value = data.subcategoria.idSubcategoria;
    }
  } catch (error) {
    console.error("Error al cargar los detalles del producto:", error);
  }
}

// Función para cargar las opciones de Categoría en el select
async function cargarOpcionesCategorias() {
  try {
    const response = await fetch("http://localhost:8080/api/categorias");
    if (!response.ok) throw new Error("No se pudieron cargar las categorías");

    const categorias = await response.json();
    const selectCategoria = document.getElementById("categoria");

    // Limpiar opciones previas
    selectCategoria.innerHTML = "<option value=''>Seleccione una categoría</option>";

    // Añadir las opciones dinámicamente
    categorias.forEach(categoria => {
      const option = document.createElement("option");
      option.value = categoria.idCategoria;
      option.textContent = categoria.nombre;
      selectCategoria.appendChild(option);
    });

    // Agregar el evento para cambiar las subcategorías cuando se seleccione una nueva categoría
    selectCategoria.addEventListener("change", function() {
      const categoriaId = this.value;
      cargarOpcionesSubcategorias(categoriaId);  // Cargar las subcategorías correspondientes
    });

  } catch (error) {
    console.error("Error al cargar las categorías:", error);
  }
}

// Función para cargar las opciones de Subcategoría en el select
async function cargarOpcionesSubcategorias(idCategoriaSeleccionada) {
   const subcategoriaSelect = document.getElementById("subcategoria");

   try {
     const response = await fetch("http://localhost:8080/api/subcategorias");
     const data = await response.json();

     // Limpiar las opciones previas
     subcategoriaSelect.innerHTML = "<option value=''>Seleccione una subcategoría</option>";

     // Añadir las opciones dinámicamente
     data.forEach((subcategoria) => {
       if (subcategoria.categoria && subcategoria.categoria.idCategoria === parseInt(idCategoriaSeleccionada)) {
         const option = document.createElement("option");
         option.value = subcategoria.idSubcategoria;
         option.text = subcategoria.nombreSubcategoria;
         subcategoriaSelect.appendChild(option);
       }
     });

   } catch (error) {
     console.error("Error cargando subcategorías:", error);
   }
}

// Función para cargar las opciones de Proveedor en el select
async function cargarOpcionesProveedores() {
  try {
    const response = await fetch("http://localhost:8080/api/proveedores");
    if (!response.ok) throw new Error("No se pudieron cargar los proveedores");

    const proveedores = await response.json();
    const selectProveedor = document.getElementById("proveedor");

    // Limpiar opciones previas
    selectProveedor.innerHTML = "<option value=''>Seleccione un proveedor</option>";

    // Añadir las opciones dinámicamente
    proveedores.forEach(proveedor => {
      const option = document.createElement("option");
      option.value = proveedor.idProveedor;
      option.textContent = proveedor.nombreProveedor;
      selectProveedor.appendChild(option);
    });

  } catch (error) {
    console.error("Error al cargar los proveedores:", error);
  }
}

// Función para actualizar el producto con los datos del formulario
async function actualizarProducto(idProducto) {
  const producto = {
    nombreProducto: document.getElementById("nombreProducto").value,
    categoria: {idCategoria: document.getElementById("categoria").value }, 
    subcategoria: {idSubcategoria: document.getElementById("subcategoria").value }, 
    descripcion: document.getElementById("descripcion").value,
    stock: parseInt(document.getElementById("stock").value),
    precioUnidadSinIva: parseFloat(document.getElementById("precioUnidad").value),
    proveedor: {idProveedor: document.getElementById("proveedor").value }, 
    activo: document.getElementById("activo").checked,
    fechaAdquisicion: document.getElementById("fechaAdquisicion").value,
    fechaCaducidad: document.getElementById("fechaCaducidad").value
  };
console.log(producto);
console.log(idProducto);
  try {
    const response = await fetch(`http://localhost:8080/api/productos/${idProducto}`, {
      method: "PATCH",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(producto),
    });

    if (response.ok) {
        console.log("Producto actualizado correctamente.")
    } else {
      alert("Error al actualizar el producto");
    }
  } catch (error) {
    console.error("Error al actualizar el producto:", error);
  }
}
