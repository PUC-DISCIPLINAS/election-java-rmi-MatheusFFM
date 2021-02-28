package network;

import models.User;
import utils.Config;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ElectionClient {

    public static void menu(boolean voted){
        System.out.println("\n======== Menu ========");
        if(voted){
            System.out.println("1 - See results\n\n0 - EXIT");
        } else {
            System.out.println("1 - See results\n2 - Vote for a senator\n0 - EXIT");
        }
        System.out.print("\n>  ");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean voted = false;

        try {
            Registry registry = LocateRegistry.getRegistry(Config.HOST);
            Election stub = (Election) registry.lookup(Config.REGISTRY_NAME);
            System.out.println("Election found");
            System.out.println("Test: result() " + stub.result("nothing"));

            System.out.println("Type your name");
            System.out.print(">  ");
            String name = in.nextLine();
            User user = new User(name);

            System.out.println("\n\nWelcome " + name + "!");
            menu(voted);
            int response = in.nextInt();

            do{
                switch (response){
                    case 1:
                        result(stub, user);
                        break;
                    case 2:
                        voted = vote(stub, user);
                        break;
                    default:
                        System.out.println("\nPlease, type a valid command");
                }
                menu(voted);
                response = in.nextInt();
            } while (response != 0);

            System.out.println("Thank you! Exiting program...");

        } catch (Exception e) {
            System.err.println("Election exception:");
            e.printStackTrace();
        }
    }

    private static void result(Election stub, User user) {
        System.out.println(user.getName() + " > RESULT");
    }

    private static boolean vote(Election stub, User user) {
        System.out.println(user.getName() + " > VOTE");
        return true;
    }

}
