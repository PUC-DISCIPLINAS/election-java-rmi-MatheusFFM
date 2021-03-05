package network;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Election extends Remote {
    boolean vote(String hash, String candidate) throws IOException;

    String result(String candidate) throws RemoteException;
}
