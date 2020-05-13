package repository.emprestimo;

public class EmprestimoPessoaBloqueadaException extends Exception {

    public EmprestimoPessoaBloqueadaException() {
        super("Pessoas com livros atrasados não podem fazer novos emprestimos.");
    }
}
