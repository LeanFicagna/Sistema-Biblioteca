package repository.pessoa;

import repository.pessoa.CPFJaCadastradoException;
import repository.pessoa.PessoaNaoCadastradoException;

import model.pessoa.Pessoa;
import java.util.List;

public interface RepositorioPessoa {

    void inserirPessoa(Pessoa pessoa) throws CPFJaCadastradoException;

    void alterarPessoa(Pessoa pessoa) throws PessoaNaoCadastradoException;

    void deletarPessoa(Pessoa pessoa) throws PessoaNaoCadastradoException;

    Pessoa buscarPessoa(String cpf) throws PessoaNaoCadastradoException;

    Pessoa buscarPessoa(String nome, String telefone) throws PessoaNaoCadastradoException;

    List<Pessoa> getAll();
}
