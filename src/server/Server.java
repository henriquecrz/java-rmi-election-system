package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            IElection election = new Election();
            Registry registry = LocateRegistry.getRegistry();

            registry.rebind("Election", election);
            System.out.println("O servidor está rodando...");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
