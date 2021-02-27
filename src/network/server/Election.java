package network.server;

import java.rmi.Remote;

public interface Election extends Remote {
    boolean vote(String hash, String candidate);
    int result(String candidate);
}
