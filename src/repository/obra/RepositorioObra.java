package repository.obra;

import java.util.List;

import model.obra.Obra;

public interface RepositorioObra {

    void inserirObra(Obra livro) throws ObraJaCadastradoException;

    void alterarObra(Obra livro) throws ObraNaoCadastradoException;

    void deletarObra(Obra livro) throws ObraNaoCadastradoException;

    Obra buscarObra(String autor, String nome, String genero) throws ObraNaoCadastradoException;

    Obra buscarObra(short codigo) throws ObraNaoCadastradoException;

    List<Obra> getAll();

    List<Obra> getAll(String autor, String nome, String genero) throws ObraNaoCadastradoException;

}
