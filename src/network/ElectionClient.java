package network;

import models.User;
import utils.Config;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ElectionClient {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        try {
            Registry registry = LocateRegistry.getRegistry(Config.HOST);
            Election stub = (Election) registry.lookup(Config.REGISTRY_NAME);
            System.out.println("Election found");
            System.out.println("Test: result() " + stub.result("nothing"));

            System.out.println("Type your name");
            System.out.print(">  ");
            String name = in.nextLine();

            User user = new User(name);
            System.out.println("You are " + user.getName() + " and your code is: " + user.getHash());

        } catch (Exception e) {
            System.err.println("Election exception:");
            e.printStackTrace();
        }
    }

}
