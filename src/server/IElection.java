package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IElection extends Remote {
    ArrayList<String> listarCandidatos() throws RemoteException;

    boolean votar(String voterId, String candidate) throws RemoteException;

    String exibirResultadoCandidato(String candidate) throws RemoteException;
}
