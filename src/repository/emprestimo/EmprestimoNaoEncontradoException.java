package repository.emprestimo;

public class EmprestimoNaoEncontradoException extends Exception {

    public EmprestimoNaoEncontradoException() {
        super("Não há emprestimo ");
    }

}
