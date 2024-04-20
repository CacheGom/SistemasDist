import java.rmi.*;
import java.rmi.server.*;

interface TiendaOnline extends Remote {
    String comprar(String producto) throws RemoteException;
    String agregarAlCarrito(String producto) throws RemoteException;
    String verCarrito() throws RemoteException;
    String pagar() throws RemoteException;
    String registrarse(String nombre, String correo, String contraseña) throws RemoteException;
    String iniciar_sesion(String Usuario, String Contraseña) throws RemoteException;
}
