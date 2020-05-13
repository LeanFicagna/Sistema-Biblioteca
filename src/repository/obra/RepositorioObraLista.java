package repository.obra;

import java.util.ArrayList;
import java.util.List;

import model.obra.Obra;

public class RepositorioObraLista implements RepositorioObra {

    List<Obra> obras;

    public RepositorioObraLista() {
        obras = new ArrayList<>();
    }

    @Override
    public void inserirObra(Obra obra) throws ObraJaCadastradoException {
        try {
            buscarObra(obra.getAutor(), obra.getTitulo(), obra.getGenero());
        } catch (ObraNaoCadastradoException ex) {
            obras.add(obra);
        }
    }

    @Override
    public void alterarObra(Obra obra) throws ObraNaoCadastradoException {
        // TODO Auto-generated method stub

    }

    @Override
    public void deletarObra(Obra obra) throws ObraNaoCadastradoException {
        if (!obras.remove(obra)) {
            throw new ObraNaoCadastradoException();
        }
    }

    @Override
    public Obra buscarObra(String autor, String nome, String genero) throws ObraNaoCadastradoException {
        for (Obra obra : obras) {
            if (obra.getAutor().equals(autor) && obra.getTitulo().equals(nome) && obra.getGenero().equals(genero)) {
                return obra;
            }
        }
        throw new ObraNaoCadastradoException();
    }

    @Override
    public Obra buscarObra(short codigo) throws ObraNaoCadastradoException {
        for (Obra obra : obras) {
            if (obra.getCodigo() == codigo) {
                return obra;
            }
        }
        throw new ObraNaoCadastradoException();
    }

    @Override
    public List<Obra> getAll() {
        return new ArrayList<>(obras);
    }

    @Override
    public List<Obra> getAll(String autor, String nome, String genero) throws ObraNaoCadastradoException {
        List<Obra> lists;
        lists = new ArrayList<>();
        for (Obra list : obras) {
            if (list.getAutor().equals(autor) || list.getTitulo().equals(nome) || list.getGenero().equals(genero)) {
                lists.add(list);
            }
        }
        if (lists.isEmpty()) {
            throw new ObraNaoCadastradoException();
        } else {
            return new ArrayList<>(lists);
        }
    }

}
