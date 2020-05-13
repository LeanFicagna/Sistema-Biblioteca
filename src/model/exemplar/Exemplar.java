package model.exemplar;

import model.obra.Obra;

public class Exemplar {

    private short quantidade;
    Obra obra;

    public Exemplar(Obra obra, short quantidade) {
        this.obra = obra;
        this.quantidade = quantidade;
    }

    public boolean getDisponivel() {
        return (!(quantidade == 0));
    }

    public Obra getObra() {
        return obra;
    }

    public short getQuantidade() {
        return quantidade;
    }

    public void retExemplar(short quantidade) {
        this.quantidade -= quantidade;
    }

    public void addExemplar(short quantidade) {
        this.quantidade += quantidade;
    }

}
