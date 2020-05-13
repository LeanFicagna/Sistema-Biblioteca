package controler;

import repository.pessoa.RepositorioPessoa;
import repository.pessoa.RepositorioPessoaLista;
import repository.emprestimo.EmprestimoNaoEncontradoException;
import repository.emprestimo.RepositorioEmprestimo;
import repository.emprestimo.RepositorioEmprestimoLista;
import repository.exemplar.ExemplarJaCadastradoException;
import repository.exemplar.ExemplarNaoEncontradoException;
import repository.exemplar.RepositorioExemplar;
import repository.exemplar.RepositorioExemplarLista;
import repository.obra.ObraJaCadastradoException;
import repository.obra.ObraNaoCadastradoException;
import repository.obra.RepositorioObra;
import repository.obra.RepositorioObraLista;
import repository.pessoa.CPFJaCadastradoException;
import repository.pessoa.PessoaNaoCadastradoException;

import java.util.List;

import model.emprestimo.Emprestimo;
import model.exemplar.Exemplar;
import model.obra.Obra;
import model.pessoa.Pessoa;
import repository.emprestimo.EmprestimoDuplicadoException;
import repository.emprestimo.EmprestimoPessoaBloqueadaException;

public class ControladorBiblioteca {

    private RepositorioPessoa repositorioPessoa;
    private RepositorioObra repositorioObra;
    private RepositorioExemplar repositorioExemplar;
    private RepositorioEmprestimo repositorioEmprestimo;

    public ControladorBiblioteca() throws Exception {
        repositorioPessoa = new RepositorioPessoaLista();
        repositorioObra = new RepositorioObraLista();
        repositorioEmprestimo = new RepositorioEmprestimoLista();
        repositorioExemplar = new RepositorioExemplarLista();
    }

    public void inserirPessoa(Pessoa pessoa) throws CPFJaCadastradoException {
        repositorioPessoa.inserirPessoa(pessoa);
    }

    public void alterarPessoa(Pessoa pessoa) throws PessoaNaoCadastradoException {
        repositorioPessoa.alterarPessoa(pessoa);
    }

    public Pessoa buscarPessoa(String cpf) throws PessoaNaoCadastradoException {
        return repositorioPessoa.buscarPessoa(cpf);
    }

    public Pessoa buscarPessoa(String nome, String telefone) throws PessoaNaoCadastradoException {
        return repositorioPessoa.buscarPessoa(nome, telefone);
    }

    public void excluirPessoa(Pessoa pessoa) throws ControladorException, PessoaNaoCadastradoException {
        repositorioPessoa.deletarPessoa(pessoa);
    }

    public List<Pessoa> getAllPessoa() {
        return repositorioPessoa.getAll();
    }
    //

    public void inserirObra(Obra livro) throws ObraJaCadastradoException {
        repositorioObra.inserirObra(livro);
    }

    public Obra buscarObra(short codigo) throws ObraNaoCadastradoException {
        return repositorioObra.buscarObra(codigo);
    }

    public Obra buscarObra(String autor, String titulo, String genero) throws ObraNaoCadastradoException {
        return repositorioObra.buscarObra(autor, titulo, genero);
    }

    public void excluirObra(Obra obra) throws ObraNaoCadastradoException {
        repositorioObra.deletarObra(obra);
    }

    public List<Obra> getAllLivros() {
        return repositorioObra.getAll();
    }

    public List<Obra> getAllLivros(String autor, String nome, String genero) throws ObraNaoCadastradoException {
        return repositorioObra.getAll(autor, nome, genero);
    }
    //

    public void addExemplar(Exemplar exemplar, short quantidade) throws ExemplarNaoEncontradoException {
        exemplar.addExemplar(quantidade);
    }

    public void inserirExemplar(Exemplar exemplar) throws ExemplarJaCadastradoException {
        repositorioExemplar.cadastra(exemplar);
    }

    public Exemplar buscarExemplar(String autor, String titulo, String genero) throws ExemplarNaoEncontradoException {
        return repositorioExemplar.buscarExemplar(titulo, genero, autor);
    }

    public Exemplar buscarExemplar(short codigo) throws ExemplarNaoEncontradoException {
        return repositorioExemplar.buscarExemplar((short) codigo);
    }

    /*public void excluirObra( Obra livro ) throws ObraNaoCadastradoException {
        repositorioObra.deletarObra( livro );
    }*/
    public List<Exemplar> getAllExemplar() {
        return repositorioExemplar.getAll();
    }

    public List<Exemplar> getAllExemplar(String autor, String titulo, String genero) throws ExemplarNaoEncontradoException {
        return repositorioExemplar.getAll(autor, titulo, genero);
    }

    //TODO
    public void novoEmprestimo(Pessoa pessoa, short codigo, Exemplar exemplar) throws PessoaNaoCadastradoException, ExemplarNaoEncontradoException, EmprestimoNaoEncontradoException, EmprestimoDuplicadoException, EmprestimoPessoaBloqueadaException {
        if(repositorioEmprestimo.buscarEmprestimo(pessoa, exemplar) == null) {
            Emprestimo emprestimo = new Emprestimo(exemplar, pessoa);
            repositorioEmprestimo.emprestar(emprestimo);
            exemplar.retExemplar((short) 1);
        } else
            throw new EmprestimoDuplicadoException();
    }

    //AlterarEmprestimo
    public void devolucaoEmprestimo(Emprestimo emprestimo) throws ControladorException, EmprestimoNaoEncontradoException {
        repositorioEmprestimo.removeEmprestimo(emprestimo);
        emprestimo.getExemplar().addExemplar((short) 1);
    }

    public Emprestimo buscarEmprestimo(Pessoa pessoa, Exemplar exemplar) throws EmprestimoNaoEncontradoException {
        return repositorioEmprestimo.buscarEmprestimo(pessoa, exemplar);
    }

    public List<Emprestimo> getAllEmprestimo() throws EmprestimoNaoEncontradoException {
        return repositorioEmprestimo.getAll();
    }

    public List<Emprestimo> getAllEmprestimo(Pessoa pessoa) throws EmprestimoNaoEncontradoException {
        return repositorioEmprestimo.getAll(pessoa);
    }
}
