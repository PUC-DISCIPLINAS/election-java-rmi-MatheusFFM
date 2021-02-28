package network;

import models.User;
import utils.Config;
import utils.SenatorReader;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ElectionServer implements Election {

    private static Map<User, String> usersVotes = new HashMap<User, String>();
    private static Map<String, Integer> senatorResults = new HashMap<String, Integer>();

    public ElectionServer() {
    }

    @Override
    public String vote(String hash, String candidate) {
        return "oi";
    }

    @Override
    public String result(String candidate) {
        if(!senatorResults.containsKey(candidate)){
            return null;
        }
        return senatorResults.get(candidate).toString();
    }

    public static void main(String[] args) {
        try{
            ElectionServer obj = new ElectionServer();
            Election stub = (Election) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.createRegistry(Config.HOST);
            registry.rebind(Config.REGISTRY_NAME, stub);
            System.out.println("Server ready");
            senatorResults = new SenatorReader(Config.FILE_NAME).read();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
