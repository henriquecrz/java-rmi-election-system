package client;

import server.Election;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;
import java.util.Scanner;

public class Client {

    private static Registry registry;
    private static Election server;

    public static void main(String[] args) {
        startServer();
        showMenu();
    }

    private static void startServer() {
        try {
            registry = LocateRegistry.getRegistry("localhost");
            server = (Election) registry.lookup("Election");
        } catch (RemoteException | NotBoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void showMenu() {
        Scanner input = new Scanner(System.in);

        System.out.println("Nome do eleitor: ");

        String eleitor = input.next();
        String md5 = getNomeMD5(eleitor);

        int opcao;

        do {
            System.out.println("1 - Exibir candidatos");
            System.out.println("2 - Votar");
            System.out.println("3 - Exibir resultados");
            System.out.println("4 - Trocar usuário");
            System.out.println("0 - Sair");

            opcao = input.nextInt();

            switch (opcao) {
                case 1:
                    exibirCandidatos();

                    break;
                case 2:                    
                    votar(md5, input.next());

                    break;
                case 3:                    
                    exibirResultadoCandidato(input.next());

                    break;
                case 4:
                    System.out.println("Nome do eleitor: ");
                    eleitor = input.next();
                    md5 = getNomeMD5(eleitor);

                    break;
                default:
                    break;
            }

            System.out.println();
        } while (opcao != 0);

        input.close();
    }

    public static void exibirCandidatos() {
        try {
            for (String candidato : server.listarCandidatos()) {
                System.out.println(candidato);
            }
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void votar(String identificador, String candidato) {
        try {
            System.out.println("Digite o número do candidato: ");

            if (server.votar(identificador, candidato)) {
                System.out.println("Seu voto foi computado...");
            } else {
                System.out.println("Não foi possível computador o voto...");
            }
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void exibirResultadoCandidato(String candidato) {
        try {
            System.out.println("Digite o número do candidato: ");
            System.out.println(server.exibirResultadoCandidato(candidato));
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String getNomeMD5(String nome) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] array = messageDigest.digest(nome.getBytes());
            StringBuffer stringBuffer = new StringBuffer();

            for (int i = 0; i < array.length; ++i) {
                stringBuffer.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }

            return stringBuffer.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
