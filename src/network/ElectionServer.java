package network;

import utils.Config;
import utils.SenatorReader;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ElectionServer implements Election {

    private static Map<String, String> usersVotes = new HashMap<String, String>(); // User hash, user vote
    private static Map<String, Integer> senatorResults = new HashMap<String, Integer>(); //senator number, senator votes

    public ElectionServer() {
    }

    @Override
    public boolean vote(String hash, String candidate) {
        if (usersVotes.containsKey(hash) || !senatorResults.containsKey(candidate)) {
            return false;
        }
        usersVotes.put(hash, candidate);
        senatorResults.put(candidate, senatorResults.get(candidate) + 1);
        return true;
    }

    @Override
    public String result(String candidate) {
        if (!senatorResults.containsKey(candidate)) {
            return null;
        }
        return senatorResults.get(candidate).toString();
    }

    public static void main(String[] args) {
        try {
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
