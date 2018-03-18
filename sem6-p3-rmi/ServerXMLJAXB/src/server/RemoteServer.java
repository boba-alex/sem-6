package server;

import exceptions.MyDAOException;
import services.MyRemoteService;
import services.MyXMLJAXBService;

import java.rmi.AccessException;
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
