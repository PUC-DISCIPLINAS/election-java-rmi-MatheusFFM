package network;

import models.User;
import utils.Config;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ElectionClient {
    private static Scanner in = new Scanner(System.in);

    private static void menu(boolean voted) {
        System.out.println("\n======== Menu ========");
        if (voted) {
            System.out.println("1 - See results\n\n0 - EXIT");
        } else {
            System.out.println("1 - See results\n2 - Vote for a senator\n0 - EXIT");
        }
        System.out.print("\n>  ");
    }

    public static void main(String[] args) {
        boolean voted = false;

        System.out.println("Type your name");
        System.out.print(">  ");
        String name = in.nextLine();
        User user = new User(name);
        System.out.println("\n\nWelcome " + name + "!");

        int response = 1;
        int tries = 0;
        String number = null;
        boolean menuDialog = true;
        boolean connected = false;

        while (tries < Config.MAX_TRIES) {
            try {
                do {
                    Registry registry = LocateRegistry.getRegistry(Config.HOST);
                    Election stub = (Election) registry.lookup(Config.REGISTRY_NAME);
                    connected = true;
                    if (menuDialog) {
                        menu(voted);
                        response = in.nextInt();
                    }
                    switch (response) {
                        case 1:
                            if (menuDialog)
                                number = askNumber();
                            result(stub, user, number);
                            break;
                        case 2:
                            if (!voted) {
                                if (menuDialog)
                                    number = askNumber();
                                voted = vote(stub, user, number);
                            }
                            break;
                        default:
                            System.out.println("\nPlease, type a valid command");
                    }
                    menuDialog = true;
                    connected = false;
                    tries = 0;
                } while (response != 0);
            } catch (Exception e) {
                if (connected) {
                    menuDialog = false;
                }
                tries++;
                System.out.println("Reconnecting to server...");
                try {
                    Thread.sleep(Config.TRIES_INTERVAL);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        System.out.println("Exiting program...");

    }

    private static String askNumber() {
        in.nextLine();
        System.out.println("Type the candidate number: ");
        System.out.print(">  ");
        return in.nextLine();
    }

    private static void result(Election stub, User user, String number) throws RemoteException {
        String votes = null;
        votes = stub.result(number);

        if (votes == null) {
            System.out.println("Couldn't find a candidate with number " + number);
        } else {
            System.out.println("Candidate " + number + " has " + votes + " votes");
        }
    }

    private static boolean vote(Election stub, User user, String number) throws RemoteException {
        boolean result = stub.vote(user.getHash(), number);
        if (result) {
            System.out.println("Success! Your vote was computed");
        } else {
            System.out.println("You can't vote in " + number);
        }
        return result;
    }

}
