package server;

import services.MyRemoteService;
import services.MyXMLStAXService;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Класс сервера (<a href="https://docs.oracle.com/javase/7/docs/api/java/rmi/server/package-summary.html">Java RMI Server</a>), обращающийся к процедурам для изменения данных.
 *
 * @author Polischuk Akexander
 * @version 1.0
 * @see MyRemoteService
 */
public class RemoteServer {

    /**
     * Адрес для обращения клиентов по сети.
     */
    private static final String BINDING_NAME = "local/MyService";

    /**
     * Основной метод работы сервера.
     *
     * @param args параметры командной строки
     * @throws RemoteException исключение {@link RemoteException}
     */
    public static void main(String[] args) throws RemoteException{
        Registry registry = null;

        try {
            registry = LocateRegistry.createRegistry(4396);
            System.out.println("Registry created!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        MyRemoteService myService = null;
        try{
             myService = new MyXMLStAXService();
        }
        catch(RuntimeException exception){
            System.out.println("SAX EXCEPTION!");
        }


        Remote stub = null;

        try {
            stub = UnicastRemoteObject.exportObject(myService, 0);
           } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            registry.bind(BINDING_NAME, stub);
            System.out.println("MyService name bound");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
