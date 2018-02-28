package server;

import services.MyRemoteService;
import services.MySQLService;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by User on 28.02.2018.
 */
public class RemoteServer {

    private static final String BINDING_NAME = "local/MyService";

    public static void main(String[] args) {
        Registry registry = null;

        try {
            registry = LocateRegistry.createRegistry(4396);
            System.out.println("Registry created!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        MyRemoteService myService = new MySQLService();

        Remote stub = null;

        try {
            stub = UnicastRemoteObject.exportObject(myService, 0);
           // stub = UnicastRemoteObject.exportObject(myService, 4396);
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
