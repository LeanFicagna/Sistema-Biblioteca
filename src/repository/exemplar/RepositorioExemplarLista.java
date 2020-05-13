package repository.exemplar;

import java.util.ArrayList;
import java.util.List;

import model.exemplar.Exemplar;
import model.obra.Obra;
import repository.obra.ObraNaoCadastradoException;

public class RepositorioExemplarLista implements RepositorioExemplar {

    List<Exemplar> exemplares;

    public RepositorioExemplarLista() {
        exemplares = new ArrayList<>();
    }

    @Override
    public void cadastra(Exemplar exemplar) throws ExemplarJaCadastradoException {
        try {
            buscarExemplar(exemplar.getObra().getTitulo(), exemplar.getObra().getGenero(), exemplar.getObra().getGenero());
            throw new ExemplarJaCadastradoException();
        } catch (ExemplarNaoEncontradoException ex) {
            exemplares.add(exemplar);
        }
    }

    @Override
    public Exemplar buscarExemplar(String titulo, String genero, String autor) throws ExemplarNaoEncontradoException {
        for (Exemplar exemplar : exemplares) {
            if (exemplar.getObra().getTitulo().equals(titulo) && exemplar.getObra().getGenero().equals(genero) && exemplar.getObra().getAutor().equals(autor)) {
                return exemplar;
            }
        }
        throw new ExemplarNaoEncontradoException();
    }

    @Override
    public Exemplar buscarExemplar(short codigo) {
        for (Exemplar exemplar : exemplares) {
            if (exemplar.getObra().getCodigo() == codigo) {
                return exemplar;
            }
        }
        return null;
    }

    @Override
    public List<Exemplar> getAll() {
        return new ArrayList<>(exemplares);
    }

    @Override
    public List<Exemplar> getAll(String autor, String nome, String genero) throws ExemplarNaoEncontradoException {
        List<Exemplar> lists;
        lists = new ArrayList<>();
        for (Exemplar list : exemplares) {
            if (list.getObra().getAutor().equals(autor) || list.getObra().getTitulo().equals(nome) || list.getObra().getGenero().equals(genero)) {
                lists.add(list);
            }
        }
        if (lists.isEmpty()) {
            throw new ExemplarNaoEncontradoException();
        } else {
            return new ArrayList<>(lists);
        }
    }

    @Override
    public void consultar(Exemplar exemplar) throws ExemplarNaoEncontradoException {
        // TODO Auto-generated method stub

    }

}
