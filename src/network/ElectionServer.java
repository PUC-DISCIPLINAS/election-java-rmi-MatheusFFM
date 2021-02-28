package network;

import utils.Config;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ElectionServer implements Election {

    public ElectionServer() {
    }

    @Override
    public String vote(String hash, String candidate) {
        return "oi";
    }

    @Override
    public String result(String candidate) {
        return "Test";
    }

    public static void main(String[] args) {
        try{
            ElectionServer obj = new ElectionServer();
            Election stub = (Election) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.createRegistry(Config.HOST);
            registry.bind(Config.REGISTRY_NAME, stub);
            System.out.println("Server ready");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
