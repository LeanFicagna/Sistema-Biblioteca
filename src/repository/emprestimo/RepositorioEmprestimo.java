package repository.emprestimo;

import java.util.List;
import model.emprestimo.Emprestimo;
import model.exemplar.Exemplar;
import model.pessoa.Pessoa;
import repository.exemplar.ExemplarNaoEncontradoException;

public interface RepositorioEmprestimo {

    public void emprestar(Emprestimo emprestimo) throws EmprestimoPessoaBloqueadaException;

    public void removeEmprestimo(Emprestimo emprestimo) throws EmprestimoNaoEncontradoException;

    Emprestimo buscarEmprestimo(Pessoa pessoa, Exemplar exemplar) throws EmprestimoNaoEncontradoException;

    List<Emprestimo> getAll() throws EmprestimoNaoEncontradoException;

    List<Emprestimo> getAll(Pessoa pessoa) throws EmprestimoNaoEncontradoException;
}
