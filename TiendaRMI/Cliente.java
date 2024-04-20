import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.rmi.RemoteException;

public class Cliente {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            // Localizar el registro RMI en el servidor
            Registry registry = LocateRegistry.getRegistry("localhost");

            // Buscar los proveedores en el registro RMI
            TiendaOnline proveedorCompra = (TiendaOnline) registry.lookup("Compra");
            TiendaOnline proveedorCarrito = (TiendaOnline) registry.lookup("Carrito");
            TiendaOnline proveedorVerCarrito = (TiendaOnline) registry.lookup("VerCarrito");
            TiendaOnline proveedorPagar = (TiendaOnline) registry.lookup("Pagar");
            
            // Debes buscar también el proveedor de GestionDeCuentas
            TiendaOnline gestionDeCuentas = (TiendaOnline) registry.lookup("GestionDeCuentas");

            int opcion;
            do {
                // Mostrar el menú principal
                System.out.println("Menú Principal:");
                System.out.println("1. Registrarse");
                System.out.println("2. Iniciar Sesión");
                System.out.println("3. Salir");
                System.out.print("Selecciona una opción: ");
                
                // Leer la opción ingresada por el usuario
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del scanner

                // Realizar una acción dependiendo de la opción seleccionada
                switch (opcion) {
                    case 1:
                        System.out.println("Registrarse...");
                        System.out.println("Ingresa tu nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.println("Ingresa tu correo: ");
                        String correo = scanner.nextLine();
                        System.out.println("Ingresa tu contraseña: ");
                        String contraseña = scanner.nextLine();
                        try {
                            // Llamada al método registrarse
                            System.out.println(gestionDeCuentas.registrarse(nombre, correo, contraseña));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        System.out.println("Iniciar Sesión...");
                        try {
                            String usuario = iniciarSesion(scanner);
                            System.out.println("Ingresa tu contraseña: ");
                            contraseña = scanner.nextLine();
                            if (usuario != null) {
                                // Si el inicio de sesión es exitoso, mostrar el menú secundario
                                menuSecundario(usuario, scanner, proveedorCompra, proveedorCarrito, proveedorVerCarrito, proveedorPagar);
                            } else {
                                // Si las credenciales son incorrectas, mostrar un mensaje y volver al menú principal
                                System.out.println("Credenciales incorrectas. Por favor, inténtalo de nuevo.");
                            }
                        } catch (Exception e) {
                            System.err.println("Error al iniciar sesión: " + e.getMessage());
                        }
                        break;
                    case 3:
                        // Salir del programa
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        // Opción inválida
                        System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
                        break;
                }
            } while (opcion != 3);

            // Cerrar el objeto Scanner después de su uso
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String iniciarSesion(Scanner scanner) throws Exception {
        // Simulando la lógica de inicio de sesión
        System.out.println("Ingresa tu usuario: ");
        String usuario = scanner.nextLine();

        // Aquí deberías tener la lógica para autenticar al usuario
        // Supongamos que el usuario es autenticado correctamente
        return usuario;
    }

    public static void menuSecundario(String usuario, Scanner scanner, TiendaOnline proveedorCompra, TiendaOnline proveedorCarrito, TiendaOnline proveedorVerCarrito, TiendaOnline proveedorPagar) throws Exception {
        int opcion;

        do {
            // Mostrar el menú secundario
            System.out.println("\nMenú Secundario:");
            System.out.println("1. Comprar");
            System.out.println("2. Carrito");
            System.out.println("3. Ver Carrito");
            System.out.println("4. Pagar");
            System.out.println("5. Actualizar datos");
            System.out.println("6. Cerrar Sesión");
            System.out.print("Selecciona una opción: ");

            // Leer la opción ingresada por el usuario
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            // Realizar una acción dependiendo de la opción seleccionada
            switch (opcion) {
                case 1:
                    System.out.println("Comprar...");
                    System.out.println("Resultado del proveedor de compra: " + proveedorCompra.comprar("Producto1"));
                    break;
                case 2:
                    System.out.println("Carrito...");
                    System.out.println("Resultado del proveedor de carrito: " + proveedorCarrito.agregarAlCarrito("Producto2"));
                    break;
                case 3:
                    System.out.println("Ver Carrito...");
                    System.out.println("Resultado del proveedor de ver carrito: " + proveedorVerCarrito.verCarrito());
                    break;
                case 4:
                    System.out.println("Pagar...");
                    System.out.println("Resultado del proveedor de pagar: " + proveedorPagar.pagar());
                    break;
                case 5:
                    System.out.println("Ingresa tu nuevo correo: ");
                    String nuevoCorreo = scanner.nextLine();
                    System.out.println("Ingresa tu nueva contraseña: ");
                    String nuevaContraseña = scanner.nextLine();
                case 6:
                    // Cerrar Sesión
                    System.out.println("Cerrando Sesión...");
                    return; // Salir del método
                default:
                    // Opción inválida
                    System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
                    break;
            }
        } while (opcion != 6);
    }
}
