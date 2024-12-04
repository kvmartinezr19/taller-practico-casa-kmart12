package servicio;
import modelo.Producto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utilidades {

    public static final String rutaArchivo = "D:\\Programación Basica\\ManejoArchivos\\gestionProductos\\productos.txt";

    public static List<Producto> leerArchivo() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                Producto producto = new Producto(
                        Integer.parseInt(datos[0]),   // ID
                        datos[1],                     // Nombre
                        datos[2],                     // Categoría
                        Double.parseDouble(datos[3]), // Precio
                        Integer.parseInt(datos[4])    // Cantidad
                );
                productos.add(producto);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return productos;
    }

    public static Producto capturarDatosProducto(Scanner scanner) {
        Producto producto = new Producto();

        System.out.println("Ingresa el código del producto:");
        producto.setIdProducto(scanner.nextInt());
        System.out.println("Ingresa el nombre del producto:");
        producto.setNombreProducto(scanner.next());
        System.out.println("Ingresa la categoría del producto:");
        producto.setCategoria(scanner.next());
        System.out.println("Ingresa el precio del producto:");
        producto.setPrecio(scanner.nextDouble());
        System.out.println("Ingresa la cantidad del producto:");
        producto.setCantidadDisponible(scanner.nextInt());
        scanner.nextLine();
        return producto;
    }

    public static void escribirArchivo(List<List<String>> data, String tipoSeparador, boolean append) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, append))) {
            for (List<String> fila : data) {
                String linea = String.join(tipoSeparador, fila);
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static Producto buscarProductoId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el ID del producto:");
        int idBuscado = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        List<Producto> productos = Utilidades.leerArchivo();

        for (Producto producto : productos) {
            if (producto.getIdProducto() == idBuscado) {
                System.out.println("Producto encontrado: " + producto);
                return producto;
            }
        }
        System.out.println("No se encontró ningún producto con el ID proporcionado.");
        return null;
    }

    public static void buscarPorNombre(Scanner scanner) {
        System.out.println("Ingresa el nombre del producto:");
        String nombreBuscado = scanner.nextLine().toLowerCase();

        List<Producto> productos = Utilidades.leerArchivo();

        boolean encontrado = false;
        for (Producto producto : productos) {
            if (producto.getNombreProducto().toLowerCase().contains(nombreBuscado)) {
                System.out.println("Producto encontrado: " + producto);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún producto con el nombre proporcionado.");
        }
    }
    public static void buscarPorCategoria(Scanner scanner) {
        System.out.println("Ingresa la categoría del producto:");
        String categoriaBuscada = scanner.nextLine().toLowerCase();

        List<Producto> productos = Utilidades.leerArchivo();

        boolean encontrado = false;
        for (Producto producto : productos) {
            if (producto.getCategoria().toLowerCase().contains(categoriaBuscada)) {
                System.out.println("Producto encontrado: " + producto);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún producto en la categoría proporcionada.");
        }
    }
}