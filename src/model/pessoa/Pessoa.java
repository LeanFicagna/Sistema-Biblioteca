package model.pessoa;

public class Pessoa {

    private boolean status;
    private String cpf;
    private String nome;
    private char sexo;
    private String telefone;

    public Pessoa(String cpf, String nome, char sexo, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.telefone = telefone;
        this.status = false;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public char getSexo() {
        return this.sexo;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }

}
