package ejecucion;
import menu.MenuPrincipal;
import menu.Validaciones;
import modelo.Inventario;
import servicio.Utilidades;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int opcion = 0;
        do {
            MenuPrincipal.menuPrincipal();
            Validaciones.opcionValida(scanner);

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Inventario.agregarProducto();
                    break;
                case 2:
                    Inventario.actualizarProducto();
                    break;
                case 3:
                    Inventario.eliminarProducto();
                    break;
                case 4:
                    int opcBusqueda = 0;
                    do {
                        MenuPrincipal.subMenuBusqueda();

                        Validaciones.opcionValida(scanner);

                        opcBusqueda = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcBusqueda) {
                            case 1:
                                Utilidades.buscarPorCategoria(scanner);
                                break;
                            case 2:
                                Utilidades.buscarPorNombre(scanner);
                                break;
                            case 3:
                                Utilidades.buscarProductoId();
                                break;
                            case 4:
                                System.out.println("Retornando al menu principal");
                                break;
                            default:
                                System.out.println("Opcion no valida");
                        }
                        Validaciones.accionContinuar(scanner);
                    } while (opcBusqueda != 4);
                    break;
                case 5:
                    Inventario.generarReporteInventario();
                    break;
                case 6:
                    Inventario.mostrarCantidadPorCategoria();
                    break;
                case 7:
                    Inventario.calcularProductoMasCaro();
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    Validaciones.accionContinuar(scanner);
                    scanner.nextLine();
            }
        } while (opcion != 8);
        scanner.close();
    }
}