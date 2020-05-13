package repository.pessoa;

public class CPFJaCadastradoException extends Exception {

    CPFJaCadastradoException() {
        super("CPF já cadastrado");
    }
}
