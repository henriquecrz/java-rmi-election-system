package server;

public class Candidato {

    private String identificador;
    private String nome;
    private String partidoPolitico;
    private int qtdVotos;

    public Candidato(String identificador, String nome, String partidoPolitico) {
        this.identificador = identificador;
        this.nome = nome;
        this.partidoPolitico = partidoPolitico;
        this.qtdVotos = 0;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public String getPartidoPolitico() {
        return partidoPolitico;
    }

    public int getQtdVotos(){
        return qtdVotos;
    }

    public void adicionarVoto() {
        this.qtdVotos++;
    }

    @Override
    public String toString() {
        return
            "Candidato:" +
            "\nNúmero: " + getIdentificador() +
            "\nNome:" + getNome() +
            "\nPartido:" + getPartidoPolitico() +
            "\nVotos:" + getQtdVotos();
    }
}
