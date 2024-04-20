import java.rmi.*;
import java.rmi.server.*;
import java.util.Scanner;



class GestionDeCuentas extends UnicastRemoteObject implements TiendaOnline {

    String RUsuario = "";
    String RCorreo = "";
    String IUsuario= "";
    String RContraseña="";

    public GestionDeCuentas() throws RemoteException {
        super();
    }

    public String registrarse(String nombre, String correo, String contraseña) throws RemoteException {
        // Aquí se implementa la lógica para registrar al usuario
        RUsuario = nombre;
        RCorreo = correo;
        RContraseña = contraseña;
        // Lógica adicional...

        return "Registro exitoso";
    }

    public String iniciar_sesion(String Usuario, String Contraseña) throws RemoteException {
        System.out.println("Ingresa tu usuario: ");
        IUsuario = Usuario; // Guarda el usuario ingresado
        System.out.println("Ingresa tu contraseña: ");
        RContraseña = Contraseña;

        if (IUsuario.equals(Usuario) && RContraseña.equals(Contraseña)) {
            System.out.println("Sesion iniciada\n\n");
            return "Sesion iniciada"; // Retorna un mensaje o algo útil
        } else {
            return "Usuario no encontrado";
        }
    }

    public String cerrar_sesion(String producto) throws RemoteException {
        return "Operación no disponible en este proveedor";
    }

    public String actualizar_info(String producto) throws RemoteException {
        return "Operación no disponible en este proveedor";
    }

    public String comprar(String producto) throws RemoteException {
        return "Operación no disponible en este proveedor";
    }

    public String agregarAlCarrito(String producto) throws RemoteException {
        return "Operación no disponible en este proveedor";
    }

    public String verCarrito() throws RemoteException {
        return "Operación no disponible en este proveedor";
    }

    public String pagar() throws RemoteException {
        return "Pago realizado con éxito";
    }


}