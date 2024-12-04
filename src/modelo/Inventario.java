package modelo;

import menu.Validaciones;
import servicio.Utilidades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventario {

    public static void agregarProducto() {
        Scanner scanner = new Scanner(System.in);

        Producto nuevoProducto = Utilidades.capturarDatosProducto(scanner);

        // Convertir el producto a una lista de cadenas
        List<String> productoDatos = new ArrayList<>();
        productoDatos.add(String.valueOf(nuevoProducto.getIdProducto()));
        productoDatos.add(nuevoProducto.getNombreProducto());
        productoDatos.add(nuevoProducto.getCategoria());
        productoDatos.add(String.valueOf(nuevoProducto.getPrecio()));
        productoDatos.add(String.valueOf(nuevoProducto.getCantidadDisponible()));

        // Llamar a escribirArchivo para agregar al archivo
        List<List<String>> data = new ArrayList<>();
        data.add(productoDatos);
        Utilidades.escribirArchivo(data, ",", true);

        System.out.println("Producto agregado con éxito.");
        Validaciones.accionContinuar(scanner);
    }

    public static void actualizarProducto() {
        Scanner scanner = new Scanner(System.in);

        Producto productoEncontrado = Utilidades.buscarProductoId();

        if (productoEncontrado == null) {
            Validaciones.accionContinuar(scanner);
            return;
        }

        System.out.println("Ingresa los nuevos datos del producto:");
        Producto nuevoProducto = Utilidades.capturarDatosProducto(scanner);

        // Actualizar el producto en la lista
        List<Producto> productos = Utilidades.leerArchivo();
        productos.remove(productoEncontrado);
        productos.add(nuevoProducto);

        // Escribir la lista actualizada en el archivo
        List<List<String>> productosComoCadenas = new ArrayList<>();
        for (Producto producto : productos) {
            List<String> productoDatos = new ArrayList<>();
            productoDatos.add(String.valueOf(producto.getIdProducto()));
            productoDatos.add(producto.getNombreProducto());
            productoDatos.add(producto.getCategoria());
            productoDatos.add(String.valueOf(producto.getPrecio()));
            productoDatos.add(String.valueOf(producto.getCantidadDisponible()));
            productosComoCadenas.add(productoDatos);
        }
        // Sobrescribir el archivo con los productos actualizados
        Utilidades.escribirArchivo(productosComoCadenas, ",", false); // false para sobrescribir
        System.out.println("Producto actualizado con éxito.");
        Validaciones.accionContinuar(scanner);
    }

    public static void eliminarProducto() {
        Scanner scanner = new Scanner(System.in);

        Producto productoEncontrado = Utilidades.buscarProductoId();

        if (productoEncontrado == null) {
            Validaciones.accionContinuar(scanner);
            return;
        }

        System.out.println("¿Seguro de que deseas eliminar este producto? (s/n):");
        String confirmacion = scanner.nextLine();

        // Cancelar la eliminación
        if (!confirmacion.equalsIgnoreCase("s")) {
            System.out.println("Eliminación cancelada.");
            return;
        }
        // Eliminar el producto de la lista
        List<Producto> productos = Utilidades.leerArchivo();
        productos.remove(productoEncontrado);

        // Escribir la lista actualizada en el archivo
        List<List<String>> productosComoCadenas = new ArrayList<>();
        for (Producto producto : productos) {
            List<String> productoDatos = new ArrayList<>();
            productoDatos.add(String.valueOf(producto.getIdProducto()));
            productoDatos.add(producto.getNombreProducto());
            productoDatos.add(producto.getCategoria());
            productoDatos.add(String.valueOf(producto.getPrecio()));
            productoDatos.add(String.valueOf(producto.getCantidadDisponible()));
            productosComoCadenas.add(productoDatos);
        }

        // Sobrescribir el archivo con los productos actualizados
        Utilidades.escribirArchivo(productosComoCadenas, ",", false);
        System.out.println("Producto eliminado con éxito.");
        Validaciones.accionContinuar(scanner);
    }

    public static void mostrarCantidadPorCategoria() {
        Scanner scanner = new Scanner(System.in);

        List<Producto> productos = Utilidades.leerArchivo();
        List<String> categoriasProcesadas = new ArrayList<>();

        System.out.println("Cantidad de productos por categoria:");
        for (Producto producto : productos) {
            String categoria = producto.getCategoria();

            if (!categoriasProcesadas.contains(categoria)) {
                // Contar productos en esta categoría
                int cantidad = 0;
                for (Producto p : productos) {
                    if (p.getCategoria().equals(categoria)) {
                        cantidad++;
                    }
                }
                System.out.println("Categoria: " + categoria + " - Cantidad: " + cantidad);

                // Marcar la categoría como procesada
                categoriasProcesadas.add(categoria);
            }
        }
        Validaciones.accionContinuar(scanner);
    }

    public static void calcularProductoMasCaro() {
        Scanner scanner = new Scanner(System.in);

        List<Producto> productos = Utilidades.leerArchivo();

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        // Inicializar el producto más caro
        Producto productoMasCaro = productos.get(0);

        // Recorrer la lista para encontrar el producto con el mayor precio
        for (Producto producto : productos) {
            if (producto.getPrecio() > productoMasCaro.getPrecio()) {
                productoMasCaro = producto;
            }
        }

        System.out.println("El producto más caro es:");
        System.out.println("ID: " + productoMasCaro.getIdProducto());
        System.out.println("Nombre: " + productoMasCaro.getNombreProducto());
        System.out.println("Categoría: " + productoMasCaro.getCategoria());
        System.out.println("Precio: " + productoMasCaro.getPrecio());
        System.out.println("Cantidad disponible: " + productoMasCaro.getCantidadDisponible());

        Validaciones.accionContinuar(scanner);
    }

    public static void generarReporteInventario() {
        Scanner scanner = new Scanner(System.in);

        List<Producto> productos = Utilidades.leerArchivo();

        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
            return;
        }

        // Variable para calcular el valor total del inventario
        double valorTotalInventario = 0;

        // Ruta del archivo de reporte
        String rutaReporte = "D:\\Programación Basica\\ManejoArchivos\\gestionProductos\\reporte_inventario.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaReporte))) {
            writer.write("Reporte de Inventario");
            writer.newLine();
            writer.write("=====================");
            writer.newLine();

            for (Producto producto : productos) {
                double valorProducto = producto.getPrecio() * producto.getCantidadDisponible();
                valorTotalInventario += valorProducto;

                writer.write("ID: " + producto.getIdProducto()
                        + ", Nombre: " + producto.getNombreProducto()
                        + ", Categoría: " + producto.getCategoria()
                        + ", Precio Unitario: " + String.format("%.2f", producto.getPrecio())
                        + ", Cantidad: " + producto.getCantidadDisponible()
                        + ", Valor: " + String.format("%.0f", valorProducto));
                writer.newLine();
            }
            writer.newLine();
            writer.write("Valor total del inventario: " + String.format("%.0f", valorTotalInventario));
            writer.newLine();

            System.out.println("Reporte generado con éxito: " + rutaReporte);
        } catch (IOException e) {
            System.err.println("Error al generar el reporte: " + e.getMessage());
        }
        Validaciones.accionContinuar(scanner);
    }
}
