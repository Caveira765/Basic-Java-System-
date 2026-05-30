package view;

import controller.FuncionarioController;
// Importe a sua exceção customizada se tiver criado
// import exceptions.ValorInvalido;
import model.*;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class FuncionarioView {

    private Scanner sc;
    private FuncionarioController controller;

    public FuncionarioView() {
        // Inicializando as variáveis de instância corretamente
        this.sc = new Scanner(System.in);
        this.controller = new FuncionarioController();
    }

    public void exibirMenu() {
        int opcao = -1;
        do {
            try {
                System.out.println("\n=== SISTEMA DE RECURSOS HUMANOS ===");
                System.out.println("1 - Cadastrar Funcionário");
                System.out.println("2 - Listar Funcionários");
                System.out.println("3 - Buscar Funcionário por ID");
                System.out.println("4 - Atualizar horas de um Freelancer");
                System.out.println("5 - Remover Funcionário");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                opcao = sc.nextInt();
                sc.nextLine(); // Limpa o "Enter" que sobra após o nextInt()

                switch (opcao) {
                    case 1:
                        cadastrarFuncionario();
                        break;
                    case 2:
                        listarFuncionarios();
                        break;
                    case 3:
                        buscarFuncionario();
                        break;
                    case 4:
                        atualizarFreelancer();
                        break;
                    case 5:
                        removerFuncionario();
                        break;
                    case 0:
                        System.out.println("Programa encerrado.");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: digite apenas números no menu.");
                sc.nextLine(); // Limpa a sujeira que o usuário digitou
            } catch (Exception e) {
                // Captura qualquer outro erro para o sistema não "quebrar" na cara do usuário
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void cadastrarFuncionario() {
        try {
            System.out.println("\n=== CADASTRAR FUNCIONÁRIO ===");
            System.out.println("1 - Freelancer");
            System.out.println("2 - Desenvolvedor");
            System.out.print("Escolha o tipo: ");

            int tipo = sc.nextInt();
            sc.nextLine(); // Limpa o buffer

            // Excelente iniciativa adicionar o NOME ao sistema!
            System.out.print("Digite o nome do funcionário: ");
            String nome = sc.nextLine();

            switch (tipo) {
                case 1:
                    System.out.print("Digite o valor da hora: ");
                    double valorHora = sc.nextDouble();

                    System.out.print("Digite as horas trabalhadas: ");
                    int horasTrabalhadas = sc.nextInt();

                    // View cria o objeto e envia para o Controller
                    controller.adicionarFuncionario(new Freelancer(nome, valorHora, horasTrabalhadas));
                    System.out.println("Freelancer cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.print("Digite o salário base do Desenvolvedor: ");
                    double salarioBase = sc.nextDouble();

                    System.out.print("Digite o bônus de produtividade: ");
                    double bonus = sc.nextDouble();

                    // View cria o objeto e envia para o Controller
                    controller.adicionarFuncionario(new Desenvolvedor(nome, salarioBase, bonus));
                    System.out.println("Desenvolvedor cadastrado com sucesso!");
                    break;

                default:
                    System.out.println("Tipo de funcionário inválido.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Erro: Você digitou um texto onde deveria ser um número.");
            sc.nextLine(); // Limpa a entrada ruim
        }
        // Descomente abaixo se for usar a exceção customizada no construtor das Models
        // catch (ValorInvalido e) {
        //     System.out.println("Erro de validação: " + e.getMessage());
        // }
    }

    private void listarFuncionarios() {
        System.out.println("\n=== LISTA DE FUNCIONÁRIOS ===");

        Map<Integer, Funcionario> funcionarios = controller.listarFuncionarios();

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado no sistema.");
        } else {
            // Percorrendo o Map (Aquele ponto de atenção para a prova!)
            for (Map.Entry<Integer, Funcionario> entry : funcionarios.entrySet()) {
                int id = entry.getKey();
                Funcionario func = entry.getValue();

                // Graças ao Polimorfismo, o calcularPagamento() saberá a fórmula correta
                System.out.println("ID: " + id + " | " + func.toString() + " | Pagamento final: R$ " + func.calcularPagamento());
            }
        }
    }

    private void buscarFuncionario() {
        try {
            System.out.print("Digite o ID do funcionário: ");
            int id = sc.nextInt();

            Funcionario func = controller.buscarPorId(id);
            if (func == null) {
                System.out.println("Funcionário não encontrado no sistema.");
            } else {
                System.out.println("Funcionário localizado:");
                System.out.println(func.toString() + " | Pagamento final: R$ " + func.calcularPagamento());
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: ID deve ser um número inteiro.");
            sc.nextLine();
        }
    }

    private void atualizarFreelancer() {
        try {
            System.out.print("Digite o ID do Freelancer que deseja atualizar: ");
            int id = sc.nextInt();

            Funcionario func = controller.buscarPorId(id);

            if (func == null) {
                System.out.println("Funcionário não encontrado.");
                return;
            }

            // O teste do instanceof garante que não vamos tentar atualizar horas de um Desenvolvedor
            if (func instanceof Freelancer) {
                System.out.print("Digite o novo valor da hora: ");
                double novoValorHora = sc.nextDouble();

                System.out.print("Digite as novas horas trabalhadas: ");
                int novasHoras = sc.nextInt();

                boolean atualizado = controller.atualizarFreelancer(id, novoValorHora, novasHoras);
                if (atualizado) {
                    System.out.println("Freelancer atualizado com sucesso!");
                } else {
                    System.out.println("Falha ao atualizar.");
                }
            } else {
                System.out.println("Erro: O ID informado pertence a um Desenvolvedor, não a um Freelancer.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Erro: valor numérico inválido.");
            sc.nextLine();
        }
    }

    private void removerFuncionario() {
        try {
            System.out.print("Digite o ID do funcionário que será desligado: ");
            int id = sc.nextInt();

            boolean removido = controller.removerFuncionario(id);
            if (removido) {
                System.out.println("Funcionário removido com sucesso.");
            } else {
                System.out.println("ID não localizado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: o ID deve ser numérico.");
            sc.nextLine();
        }
    }
}