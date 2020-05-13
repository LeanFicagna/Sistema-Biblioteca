package view;

import controler.ControladorBiblioteca;
import controler.ControladorException;
import java.time.format.DateTimeFormatter;
import repository.emprestimo.EmprestimoNaoEncontradoException;
import repository.exemplar.ExemplarJaCadastradoException;
import repository.exemplar.ExemplarNaoEncontradoException;
import repository.obra.ObraJaCadastradoException;
import repository.pessoa.CPFJaCadastradoException;
import repository.pessoa.PessoaNaoCadastradoException;
import model.emprestimo.Emprestimo;
import model.exemplar.Exemplar;
import model.obra.Obra;
import model.pessoa.Pessoa;

import java.util.List;
import java.util.Scanner;
import repository.emprestimo.EmprestimoDuplicadoException;
import repository.emprestimo.EmprestimoPessoaBloqueadaException;
import repository.obra.ObraNaoCadastradoException;

public class ProgramaBiblioteca {

    static ControladorBiblioteca controlador;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) throws Exception {
        controlador = new ControladorBiblioteca();

        byte opcao;

        do {
            insereDadosTeste();
            limpaTela();
            System.out.println("==============");
            System.out.println("MENU PRINCIPAL");
            System.out.println("==============");
            System.out.println();
            System.out.println("< 1 > Cadastro Pessoa");
            System.out.println("< 2 > Login");
            System.out.println("< 3 > Cadastro de Livros");
            System.out.println("< 4 > Cadastro de Exemplar");
            System.out.println("< 5 > Consultar acervo de livros");
            System.out.println("< 6 > Lista de Emprestimo Clientes");
            System.out.println("< 7 > Leitores Bloqueados");
            System.out.println("< 0 > Sair");
            System.out.println();
            System.out.print("Escolha uma opção: ");

            try {
                opcao = (byte) (Byte.valueOf(scanner.nextLine()));
            } catch (NumberFormatException e) {
                opcao = 0;
            }
            switch (opcao) {
                case 0:
                    limpaTela();
                    break;
                case 1:
                    menuCadastroPessoa();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    addObra();
                    break;
                case 4:
                    addExemplar();
                    break;
                case 5:
                    acervoLivros();
                    break;
                case 6:
                    listaEmprestimo();
                    break;
                case 7:
                    leitoresBloqueados();
                    break;
            }

        } while (opcao != 0);
        System.out.println("Programa terminado");
    }

    public static void limpaTela() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

    private static void insereDadosTeste() {

        try {
            Pessoa pessoa1 = new Pessoa("3", "l", 'M', "5");
            controlador.inserirPessoa(pessoa1);
            Pessoa pessoa2 = new Pessoa("1", "João Paulo", 'M', "99999-9999");
            controlador.inserirPessoa(pessoa2);
            Pessoa pessoa3 = new Pessoa("2", "Paula Leite", 'F', "88888-8888");
            controlador.inserirPessoa(pessoa3);

            Obra l1 = new Obra("George Orwel", "A Revolução dos Bichos", "Ficção");
            controlador.inserirObra(l1);
            Exemplar e1 = new Exemplar(l1, (short) 5);
            controlador.inserirExemplar(e1);

            Obra l2 = new Obra("Le", "AA", "Drama");
            controlador.inserirObra(l2);
            Exemplar e2 = new Exemplar(l2, (short) 2);
            controlador.inserirExemplar(e2);
        } catch (ExemplarJaCadastradoException | CPFJaCadastradoException | ObraJaCadastradoException ex) {
        }
    }

    public static void menuCadastroPessoa() {
        byte opcao;

        do {
            limpaTela();
            System.out.println("===============");
            System.out.println("CADASTRO PESSOA");
            System.out.println("===============");
            System.out.println();
            System.out.println("< 1 > Cadastra Pessoa");
            System.out.println("< 2 > Alterar Dados Pessoa");
            System.out.println("< 3 > Excluir Pessoa");
            System.out.println("< 4 > Listar Pessoas");
            System.out.println("< 0 > Menu principal");
            System.out.println();
            System.out.print("Escolha uma opção: ");

            try {
                opcao = (byte) (Byte.valueOf(scanner.nextLine()));
            } catch (NumberFormatException e) {
                opcao = 0;
            }
            switch (opcao) {
                case 0:
                    limpaTela();
                    break;
                case 1:
                    CadastroPessoa();
                    break;
                case 2:
                    alterarPessoa();
                    break;
                case 3:
                    excluirPessoa();
                    break;
                case 4:
                    menuListarPessoa();
            }

        } while (opcao != 0);
    }

    public static void login() {
        try {
            //TODO
            limpaTela();
            System.out.println("================");
            System.out.println("Login");
            System.out.println("================");
            System.out.println();
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            Pessoa pessoa = controlador.buscarPessoa(nome, telefone);

            int opcao;
            do {
                limpaTela();
                System.out.println("================");
                System.out.printf("Login: %s\n", pessoa.getNome());
                System.out.println("================");
                System.out.println("< 1 > Emprestar Exemplar");
                System.out.println("< 2 > Lista de Emprestimo");
                System.out.println("< 3 > Devolução de uma Obra");
                System.out.println("< 4 > Consultar acervo de livros");
                System.out.println("< 0 > Sair");
                System.out.println();
                System.out.print("Escolha uma opção: ");

                try {
                    opcao = Integer.valueOf(scanner.nextLine());
                } catch (NumberFormatException e) {
                    opcao = 0;
                }

                switch (opcao) {
                    case 0:
                        limpaTela();
                        break;
                    case 1:
                        emprestaExemplar(pessoa);
                        break;
                    case 2:
                        listaEmprestimo(pessoa);
                        break;
                    case 3:
                        devolucaoExemplar(pessoa);
                        break;
                    case 4:
                        acervoLivros();
                        break;
                }
            } while (opcao != 0);
        } catch (PessoaNaoCadastradoException e) {
            System.err.println(e.getMessage());
        }

        System.out.println();
        System.out.println("tecle <enter> para voltar");
        scanner.nextLine();
        limpaTela();
    }

    public static void acervoLivros() {
        limpaTela();

        System.out.println("=========================");
        System.out.println("Consulta Acervo de Livros");
        System.out.println("=========================");
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        try {
            List<Exemplar> exemplares;
            System.out.println("==================== ======================== =============== =========== ========= ==========");
            System.out.println("Autor                Titulo                   Gênero          Status      Codigo    Quantidade");
            System.out.println("==================== ======================== =============== =========== ========= ==========");
            if (autor.equals("") && titulo.equals("") && genero.equals("")) {
                exemplares = controlador.getAllExemplar();
            } else {
                exemplares = controlador.getAllExemplar(autor, titulo, genero);
            }
            for (Exemplar exemplar : exemplares) {
                System.out.printf("%-20s ", exemplar.getObra().getAutor());
                System.out.printf("%-24s ", exemplar.getObra().getTitulo());
                System.out.printf("%15s ", exemplar.getObra().getGenero());
                System.out.printf("%s ", (exemplar.getDisponivel() == true) ? "Disponível" : "Indisponível");
                System.out.printf("  %5d ", exemplar.getObra().getCodigo());
                System.out.printf("%10s ", exemplar.getQuantidade());
                System.out.println();
            }
            System.out.println();
        } catch (ExemplarNaoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println("tecle < enter > para voltar");
        scanner.nextLine();

    }

    // --- CADASTRA PESSOA ---
    public static void CadastroPessoa() {
        limpaTela();
        System.out.println("================");
        System.out.println("Cadastrar Pessoa");
        System.out.println("================");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Sexo: ");
        char sexo = scanner.nextLine().charAt(0);
        System.out.print("Telefone: ");
        String fone = scanner.nextLine();

        Pessoa pessoa = new Pessoa(cpf, nome, sexo, fone);

        try {
            controlador.inserirPessoa(pessoa);
            System.out.println("Pessoa cadastrada!");
        } catch (CPFJaCadastradoException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println("precione < enter > para voltar");
        scanner.nextLine();
    }

    public static void alterarPessoa() {
        limpaTela();
        System.out.println("=================");
        System.out.println("Alterar de Pessoa");
        System.out.println("=================");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        try {
            Pessoa pessoa = controlador.buscarPessoa(cpf);

            System.out.println();
            System.out.println("Nome: " + pessoa.getNome());
            System.out.print("Nome (< enter > = Não alterar): ");
            String nome = scanner.nextLine();
            if (!nome.equals("")) {
                pessoa.setNome(nome);
            }

            System.out.println("Sexo: " + pessoa.getSexo());
            System.out.print("Sexo (< enter > = Não alterar): ");
            String sexo = scanner.nextLine();
            if (!sexo.equals("")) {
                pessoa.setSexo(sexo.charAt(0));
            }

            System.out.println("Telefone: " + pessoa.getTelefone());
            System.out.print("Telefone (< enter > = Não alterar): ");
            String fone = scanner.nextLine();
            if (!fone.equals("")) {
                pessoa.setTelefone(fone);
            }

            System.out.println();

            controlador.alterarPessoa(pessoa);
            System.out.println("Pessoa Alterado!");
            System.out.println();

        } catch (PessoaNaoCadastradoException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println();
        System.out.println("tecle < enter > para voltar");
        scanner.nextLine();
    }

    public static void excluirPessoa() {
        limpaTela();
        System.out.println("=================");
        System.out.println("Excluir de Pessoa");
        System.out.println("=================");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        try {
            Pessoa pessoa = controlador.buscarPessoa(cpf);
            System.out.println();
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Sexo: " + pessoa.getSexo());
            System.out.println("Telefone: " + pessoa.getTelefone());
            System.out.println();

            System.out.print("Exclui essa pessoa? (s/n)?");
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("s")) {
                controlador.excluirPessoa(pessoa);
                System.out.println("Pessoa excluído!");
            }

        } catch (PessoaNaoCadastradoException | ControladorException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println();
        System.out.println("tecle < enter > para voltar");
        scanner.nextLine();

    }

    public static void menuListarPessoa() {
        limpaTela();
        List<Pessoa> pessoas = controlador.getAllPessoa();
        System.out.printf("============ ==================== ==== ===============\n");
        System.out.printf("CPF          Nome                 Sexo Telefone      \n");
        System.out.printf("============ ==================== ==== ===============\n");
        for (Pessoa pessoa : pessoas) {
            System.out.printf("%-12s ", pessoa.getCpf());
            System.out.printf("%-20s ", pessoa.getNome());
            System.out.printf("%-4s ", String.valueOf(pessoa.getSexo()));
            System.out.printf("%15s\n", pessoa.getTelefone());
        }

        System.out.println();
        System.out.println("tecle < enter > para voltar");
        scanner.nextLine();
    }

    public static void devolucaoExemplar(Pessoa pessoa) {
        System.out.printf("===============\n");
        System.out.printf("Devolver Livro\n");
        System.out.printf("===============\n");
        System.out.println("Digite o código do livro a devolver");
        short codigo = scanner.nextShort();
        try {
            System.out.println();
            Exemplar exemplar = controlador.buscarExemplar(codigo);
            System.out.println();
            System.out.println("Titulo: " + exemplar.getObra().getTitulo());
            System.out.println("Autor: " + exemplar.getObra().getAutor());
            System.out.println("Gênero: " + exemplar.getObra().getGenero());
            System.out.println();

            Emprestimo emprestimo = controlador.buscarEmprestimo(pessoa, exemplar);
            controlador.devolucaoEmprestimo(emprestimo);
            System.out.println("Devolvido com sucesso!");
        } catch (EmprestimoNaoEncontradoException | ExemplarNaoEncontradoException | ControladorException ex) {
            System.err.println(ex.getMessage());
        }
        scanner.nextLine();
        System.out.println();
        System.out.println("tecle < enter > para voltar");
        scanner.nextLine();
    }

    public static void emprestaExemplar(Pessoa pessoa) {
        limpaTela();
        System.out.printf("===============\n");
        System.out.printf("Emprestar Livro\n");
        System.out.printf("===============\n");
        System.out.println();
        System.out.println("Digite o código do livro a emprestar");
        short codigo = scanner.nextShort();
        System.out.println();
        try {
            scanner.nextLine();
            Obra obra = controlador.buscarObra(codigo);
            Exemplar exemplar = controlador.buscarExemplar(obra.getAutor(), obra.getTitulo(), obra.getGenero());
            System.out.println();
            System.out.println("Titulo: " + exemplar.getObra().getTitulo());
            System.out.println("Autor: " + exemplar.getObra().getAutor());
            System.out.println("Gênero: " + exemplar.getObra().getGenero());
            System.out.println();

            System.out.print("Empresta esse livro? (s/n)?");
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("s") && exemplar.getQuantidade() > 0) {
                controlador.novoEmprestimo(pessoa, codigo, exemplar);
                System.out.println("Livro emprestado!");
            } else if (resposta.equalsIgnoreCase("s") && exemplar.getQuantidade() <= 0) {
                System.err.println("Não há exemplar disponível");
            }
        } catch (EmprestimoPessoaBloqueadaException | PessoaNaoCadastradoException | ObraNaoCadastradoException | EmprestimoDuplicadoException | ExemplarNaoEncontradoException | EmprestimoNaoEncontradoException ex) {

            System.err.println(ex.getMessage());
        }
        System.out.println();
        System.out.println("tecle < enter > para voltar");
        scanner.nextLine();
    }

    public static void listaEmprestimo(Pessoa pessoa) {
        limpaTela();
        System.out.println("=========================== ============= ================ ======");
        System.out.println("Obra                        Data de Saída Data para Devol. Faltam");
        System.out.println("=========================== ============= ================ ======");
        try {
            List<Emprestimo> emprestimos = controlador.getAllEmprestimo(pessoa);
            for (Emprestimo emprestimo : emprestimos) {
                System.out.printf("%-27s ", emprestimo.getExemplar().getObra().getTitulo());
                System.out.printf("%13s ", emprestimo.getSaida().format(DateTimeFormatter.ofPattern("dd/MM/y")));
                System.out.printf("%16s ", emprestimo.getEntrega().format(DateTimeFormatter.ofPattern("dd/MM/y")));
                System.out.printf("%s Dias", emprestimo.diasFaltam());
                System.out.println();
            }
        } catch (EmprestimoNaoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println();
        System.out.println("tecle < enter > para voltar");
        scanner.nextLine();
    }

    public static void listaEmprestimo() {
        limpaTela();
        System.out.println("=========================== ========================= ================ ======");
        System.out.println("Pessoa                      Obra                      Data para Devol. Faltam");
        System.out.println("=========================== ========================= ================ ======");
        try {
            List<Emprestimo> emprestimos = controlador.getAllEmprestimo();
            for (Emprestimo emprestimo : emprestimos) {
                System.out.printf("%-27s ", emprestimo.getPessoa().getNome());
                System.out.printf("%-25s ", emprestimo.getExemplar().getObra().getTitulo());
                System.out.printf("%-4s ", emprestimo.getSaida().format(DateTimeFormatter.ofPattern("dd/MM/y")));
                System.out.printf("%5s Dias", emprestimo.diasFaltam());
                System.out.println();
            }
        } catch (EmprestimoNaoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println();
        System.out.println("tecle < enter > para voltar");
        scanner.nextLine();
    }

    public static void addObra() throws ObraNaoCadastradoException {
        limpaTela();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        Obra obra = new Obra(autor, titulo, genero);
        try {
            controlador.inserirObra(obra);
            System.out.printf("\nCodigo: %05d\n", obra.getCodigo());
            System.out.println("\nLivro adicionado!");
        } catch (ObraJaCadastradoException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println();
        System.out.println("tecle < enter > para voltar");
        scanner.nextLine();
    }

    public static void addExemplar() {
        limpaTela();
        System.out.print("Codigo: ");
        short codigo = scanner.nextShort();
        try {
            Obra obra = controlador.buscarObra(codigo);
            System.out.print("Quantidade: ");
            short quantidade = scanner.nextShort();
            Exemplar exemplar = controlador.buscarExemplar(codigo);
            if (exemplar == null) {
                exemplar = new Exemplar(obra, (short) quantidade);
                controlador.inserirExemplar(exemplar);
            } else {
                exemplar.addExemplar(quantidade);
            }
            System.out.println();
            System.out.println("Exemplar(es) adicionado(s)!");
        } catch (ObraNaoCadastradoException | ExemplarJaCadastradoException | ExemplarNaoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }
        scanner.nextLine();
        System.out.println("tecle < enter > para voltar");
        scanner.nextLine();
    }

    public static void leitoresBloqueados() {
        limpaTela();
        System.out.println("============ ==================== ==== ===============");
        System.out.println("CPF          Nome                 Sexo Telefone      ");
        System.out.println("============ ==================== ==== ===============");
        boolean cont = false;
        try {
            List<Emprestimo> emprestimos = controlador.getAllEmprestimo();
            for (Emprestimo emprestimo : emprestimos) {
                emprestimo.verificacaoStatus();
                if (emprestimo.getPessoa().getStatus() == true) {
                    cont = true;
                    System.out.printf("%-12s ", emprestimo.getPessoa().getCpf());
                    System.out.printf("%-20s ", emprestimo.getPessoa().getNome());
                    System.out.printf("%4s ", String.valueOf(emprestimo.getPessoa().getSexo()));
                    System.out.printf("%15s\n", emprestimo.getPessoa().getTelefone());
                }
            }
        } catch (EmprestimoNaoEncontradoException ex) {
        }
        if (cont == false) {
            System.err.println("Não há bloqueios, sistema bloqueia automaticamente após atrazo(s).");
        }
        System.out.println();
        System.out.println("tecle < enter > para voltar");
        scanner.nextLine();
    }
}
