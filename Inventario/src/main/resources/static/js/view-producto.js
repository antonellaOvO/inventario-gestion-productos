document.addEventListener("DOMContentLoaded", function () {
  const urlParams = new URLSearchParams(window.location.search);
  const idProducto = urlParams.get("idProducto");

  if (idProducto) {
    detallesProductoPorId(idProducto);
  }
});

function detallesProductoPorId(idProducto) {
  const productoUrl = `http://localhost:8080/api/productos/${idProducto}`;

  fetch(productoUrl)
    .then((response) => response.json())
    .then((data) => {
        console.log(data);
      // Asigna cada campo en el HTML con el valor correspondiente de `data`
      document.getElementById("productoId").textContent = data.idProducto;
      document.getElementById("nombreProducto").textContent = data.nombreProducto;
      document.getElementById("nombreProductoSpan").textContent = data.nombreProducto;
      document.getElementById("categoria").textContent = data.subcategoria.categoria.nombre;
      document.getElementById("subcategoria").textContent = data.subcategoria.nombreSubcategoria;
      document.getElementById("descripcion").textContent = data.descripcion;
      document.getElementById("stock").textContent = data.stock;
      document.getElementById("precioUnidad").textContent = data.precioUnidadSinIva + "€";
      document.getElementById("tipoIva").textContent = data.iva.tipoIva;
      document.getElementById("incrementoIva").textContent = data.iva.incremento;
      document.getElementById("precioConIva").textContent = data.precioUnidadConIva + "€";
      document.getElementById("precioTotalSinIva").textContent = data.precioTotalSinIva + "€";
      document.getElementById("precioTotalConIva").textContent = data.precioTotalConIva + "€";
      document.getElementById("proveedor").textContent = data.proveedor.nombreProveedor;
      document.getElementById("telefonoProveedor").textContent = data.proveedor.telefono;
      document.getElementById("emailProveedor").textContent = data.proveedor.email;
      document.getElementById("disponibilidad").textContent = data.disponibilidad ? "Disponible" : "No disponible";
      document.getElementById("activo").textContent = data.activo ? "Activo" : "Inactivo";
      document.getElementById("fechaAdquisicion").textContent = data.fechaAdquisicion;
      document.getElementById("fechaCaducidad").textContent = data.fechaCaducidad;
      document.getElementById("fechaAlta").textContent = data.fechaAlta;
      document.getElementById("fechaBaja").textContent = data.fechaBaja === null ? "N/A" : `${data.fechaBaja}`;
      document.getElementById("fechaActualizacion").textContent = data.fechaActualizacion;
    })
    .catch((error) => console.error("Error al obtener los detalles del producto:", error));
}
