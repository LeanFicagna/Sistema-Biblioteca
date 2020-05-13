package repository.emprestimo;

import java.util.ArrayList;
import java.util.List;
import model.emprestimo.Emprestimo;
import model.exemplar.Exemplar;
import model.pessoa.Pessoa;
import repository.exemplar.ExemplarNaoEncontradoException;

public class RepositorioEmprestimoLista implements RepositorioEmprestimo {

    private List<Emprestimo> emprestimos;

    public RepositorioEmprestimoLista() {
        emprestimos = new ArrayList<>();
    }

    @Override
    public void emprestar(Emprestimo emprestimo) throws EmprestimoPessoaBloqueadaException{
        if( emprestimo.getPessoa().getStatus() == true )
            throw new EmprestimoPessoaBloqueadaException();
        emprestimos.add(emprestimo);
    }

    @Override
    public void removeEmprestimo(Emprestimo emprestimo) throws EmprestimoNaoEncontradoException {
        if (!emprestimos.remove(emprestimo)) {
            throw new EmprestimoNaoEncontradoException();
        }
    }

    @Override
    public Emprestimo buscarEmprestimo(Pessoa pessoa, Exemplar exemplar) throws EmprestimoNaoEncontradoException {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getPessoa() == pessoa && emprestimo.getExemplar() == exemplar) {
                return emprestimo;
            }
        }
        return null;
    }

    @Override
    public List<Emprestimo> getAll() throws EmprestimoNaoEncontradoException {
        if (emprestimos.isEmpty()) {
            throw new EmprestimoNaoEncontradoException();
        }
        return new ArrayList<>(emprestimos);
    }

    @Override
    public List<Emprestimo> getAll(Pessoa pessoa) throws EmprestimoNaoEncontradoException {
        List<Emprestimo> listEmprestimo = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getPessoa().getNome().equals(pessoa.getNome())) {
                listEmprestimo.add(emprestimo);
            }
        }
        if (listEmprestimo.isEmpty()) {
            throw new EmprestimoNaoEncontradoException();
        }
        return new ArrayList<>(listEmprestimo);
    }

}
