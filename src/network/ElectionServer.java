package network;

import utils.Config;
import utils.SenatorFile;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.TreeMap;

public class ElectionServer implements Election {

    private static Map<String, String> usersVotes = new TreeMap<>(); // User hash, user vote
    private static Map<String, Integer> senatorResults = new TreeMap<>(); //senator number, senator votes

    public ElectionServer() {
    }

    @Override
    public boolean vote(String hash, String candidate) throws IOException {
        if (usersVotes.containsKey(hash) || !senatorResults.containsKey(candidate)) {
            return false;
        }
        usersVotes.put(hash, candidate);
        senatorResults.put(candidate, senatorResults.get(candidate) + 1);
        SenatorFile.write(senatorResults, Config.FILE_CACHE_NAME);
        SenatorFile.write(usersVotes, Config.FILE_VOTES_CACHE);
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
            SenatorFile senatorFile = new SenatorFile();
            senatorFile.read();
            senatorResults = senatorFile.getSenators();
            usersVotes = senatorFile.getUserVotes();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
