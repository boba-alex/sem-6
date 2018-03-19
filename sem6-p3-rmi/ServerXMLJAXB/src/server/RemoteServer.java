package server;

import exceptions.MyDAOException;
import services.MyRemoteService;
import services.MyXMLJAXBService;

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
     */
    public static void main(String[] args) {
        //avoid deleting reference from GC
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(4396);
            System.out.println("Registry created!");

            MyRemoteService myService = new MyXMLJAXBService();
            Remote stub = UnicastRemoteObject.exportObject(myService, 0);
            registry.rebind(BINDING_NAME, stub);
            System.out.println("MyService name bound");
        } catch (MyDAOException | RemoteException e) {
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
