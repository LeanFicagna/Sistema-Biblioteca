package repository.pessoa;

public class PessoaNaoCadastradoException extends Exception {

    PessoaNaoCadastradoException() {
        super("Pessoa não cadastrada");
    }
}
