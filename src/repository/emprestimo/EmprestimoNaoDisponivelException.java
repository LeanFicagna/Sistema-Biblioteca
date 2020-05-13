package repository.emprestimo;

public class EmprestimoNaoDisponivelException extends Exception {

    public EmprestimoNaoDisponivelException() {
        super("Exemplar não disponível");
    }
}
