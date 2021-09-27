package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;

public class Election extends UnicastRemoteObject implements IElection {

    public static ArrayList<Candidato> candidatos = new ArrayList<>();
    public ArrayList<String> eleitores = new ArrayList<>();

    public Election() throws RemoteException {
        super();
        preencheCandidatos("assets/senadores.csv");
    }

    @Override
    public ArrayList<String> listarCandidatos() throws RemoteException {
        ArrayList<String> names = new ArrayList<>();

        for (Candidato candidato : candidatos) {
            names.add(candidato.getNome() +
                " " + candidato.getPartidoPolitico() +
                " Número: " + candidato.getIdentificador());
        }

        return names;
    }

    @Override
    public boolean votar(String identificador, String candidato) throws RemoteException {
        if (eleitores.contains(identificador)) {
            return false;
        }

        for (Candidato item : candidatos) {
            if (Objects.equals(item.getIdentificador(), candidato)) {
                item.adicionarVoto();
                eleitores.add(identificador);

                return true;
            }
        }

        return false;
    }

    @Override
    public String exibirResultadoCandidato(String candidate) throws RemoteException {
        for (Candidato candidato : candidatos) {
            if (Objects.equals(candidato.getIdentificador(), candidate)) {
                return candidato.toString();
            }
        }

        return "Candidato não encontrado...";
    }

    private static void preencheCandidatos(String arquivo) {
        Path caminhoArquivo = Paths.get(arquivo);

        try (BufferedReader bufferedReader = Files.newBufferedReader(caminhoArquivo, StandardCharsets.ISO_8859_1)) {
            bufferedReader.readLine();

            String dado = bufferedReader.readLine();

            while (dado != null) {
                String[] dados = dado.split(";");
                Candidato candidato = getCandidato(dados);

                candidatos.add(candidato);

                dado = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Candidato getCandidato(String[] metadata) {
        String identificador = metadata[0];
        String nome = metadata[1];
        String partido = metadata[2];

        return new Candidato(identificador, nome, partido);
    }
}
