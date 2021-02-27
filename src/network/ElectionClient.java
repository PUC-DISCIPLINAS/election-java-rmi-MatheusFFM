package network;

import network.server.Election;
import utils.Config;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ElectionClient {
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Registry registry = LocateRegistry.getRegistry(Config.LOCALHOST);
            Election election = (Election) registry.lookup(Config.REGISTRY_NAME);
            System.out.println("Election found");
        } catch (Exception e) {
            System.err.println("Election exception:");
            e.printStackTrace();
        }
    }

}
