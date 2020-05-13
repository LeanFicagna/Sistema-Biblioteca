package repository.pessoa;

import model.pessoa.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPessoaLista implements RepositorioPessoa {

    List<Pessoa> pessoas;

    public RepositorioPessoaLista() {
        pessoas = new ArrayList<>();
    }

    @Override
    public void inserirPessoa(Pessoa pessoa) throws CPFJaCadastradoException {
        try {
            buscarPessoa(pessoa.getCpf());
            throw new CPFJaCadastradoException();
        } catch (PessoaNaoCadastradoException ex) {
            pessoas.add(pessoa);
        }
    }

    @Override
    public void alterarPessoa(Pessoa pessoa) throws PessoaNaoCadastradoException {
        //Em memória
    }

    @Override
    public void deletarPessoa(Pessoa pessoa) throws PessoaNaoCadastradoException {
        if (!pessoas.remove(pessoa)) {
            throw new PessoaNaoCadastradoException();
        }
    }

    @Override
    public Pessoa buscarPessoa(String cpf) throws PessoaNaoCadastradoException {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpf)) {
                return pessoa;
            }
        }
        throw new PessoaNaoCadastradoException();

    }

    @Override
    public Pessoa buscarPessoa(String nome, String telefone) throws PessoaNaoCadastradoException {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getNome().equals(nome)) {
                if (pessoa.getTelefone().equals(telefone)) {
                    return pessoa;
                }
            }
        }
        throw new PessoaNaoCadastradoException();

    }

    @Override
    public List<Pessoa> getAll() {
        return new ArrayList<>(pessoas);
    }

}
