package repository.emprestimo;

public class EmprestimoDuplicadoException extends Exception {

    public EmprestimoDuplicadoException() {
        super("Não é possível empresta o mesmo livro duas vezes");
    }
}
