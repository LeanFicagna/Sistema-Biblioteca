package repository.obra;

public class ObraNaoCadastradoException extends Exception {

    public ObraNaoCadastradoException() {
        super("Livro não cadastrado");
    }
}
