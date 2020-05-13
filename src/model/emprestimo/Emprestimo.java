package model.emprestimo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import model.exemplar.Exemplar;
import model.pessoa.Pessoa;

public class Emprestimo {

    private Exemplar exemplar;
    private Pessoa pessoa;
    private LocalDate hoje;
    private LocalDate entrega;
    private LocalDate saida;

    public Emprestimo(Exemplar exemplar, Pessoa pessoa) {
        this.pessoa = pessoa;
        this.exemplar = exemplar;
        this.saida = LocalDate.now();
        this.entrega = saida.plusDays(7);
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDate getEntrega() {
        return saida.plusDays(7);
    }

    public void verificacaoStatus() {
        this.hoje = LocalDate.now();
        short dias = (short) ChronoUnit.DAYS.between(hoje, entrega);
        if (pessoa.getStatus() == false && dias < 0) {
            pessoa.setStatus(true);
        }
    }

    public LocalDate getSaida() {
        return saida;
    }

    public short diasFaltam() {
        this.hoje = LocalDate.now();
        short dias = (short) ChronoUnit.DAYS.between(hoje, entrega);
        if (dias > 0) {
            return dias;
        } else {
            return 0;
        }
    }
}
