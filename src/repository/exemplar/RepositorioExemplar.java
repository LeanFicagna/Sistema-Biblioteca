package repository.exemplar;

import java.util.List;

import model.exemplar.Exemplar;
import model.obra.Obra;
import repository.obra.ObraNaoCadastradoException;

public interface RepositorioExemplar {

    void cadastra(Exemplar exemplar) throws ExemplarJaCadastradoException;

    void consultar(Exemplar exemplar) throws ExemplarNaoEncontradoException;

    Exemplar buscarExemplar(String titulo, String genero, String autor) throws ExemplarNaoEncontradoException;

    Exemplar buscarExemplar(short codigo);

    List<Exemplar> getAll(String autor, String nome, String genero) throws ExemplarNaoEncontradoException;

    List<Exemplar> getAll();
}
