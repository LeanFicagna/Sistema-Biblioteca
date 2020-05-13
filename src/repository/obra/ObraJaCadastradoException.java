package repository.obra;

public class ObraJaCadastradoException extends Exception {

    public ObraJaCadastradoException() {
        super("Obra já cadastrado");
    }
}
