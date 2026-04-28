package interfaceusuario;

import model.Chamado;
import model.Prioridade;
import model.Tecnico;
import model.Usuario;
import sistema.SistemaSuporte;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final SistemaSuporte sistema;
    private final Scanner scanner;

    public Menu() {
        this.sistema = new SistemaSuporte();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            imprimirCabecalho();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> abrirChamado();
                case 3 -> atenderProximoChamado();
                case 4 -> listarFilaChamados();
                case 5 -> verHistorico();
                case 6 -> buscarChamadoPorId();
                case 7 -> exibirRelatorios();
                case 0 -> System.out.println("\nEncerrando sistema... Até logo!");
                default -> System.out.println("\nOpção inválida. Tente novamente.");
            }

            if (opcao != 0) {
                pausar();
            }
        } while (opcao != 0);
    }

    private void imprimirCabecalho() {
        System.out.println("\n====================================================");
        System.out.println("   SIMULADOR DE SUPORTE TÉCNICO DE INFORMÁTICA");
        System.out.println("====================================================");
        System.out.println("1 - Cadastrar usuário");
        System.out.println("2 - Abrir chamado");
        System.out.println("3 - Atender próximo chamado");
        System.out.println("4 - Listar fila de chamados");
        System.out.println("5 - Ver histórico");
        System.out.println("6 - Buscar chamado por ID");
        System.out.println("7 - Relatórios");
        System.out.println("0 - Sair");
        System.out.println("====================================================");
    }

    private void cadastrarUsuario() {
        System.out.println("\n--- Cadastro de Usuário ---");
        String nome = lerTexto("Nome: ");
        String setor = lerTexto("Setor: ");

        Usuario usuario = sistema.cadastrarUsuario(nome, setor);
        System.out.println("Usuário cadastrado com sucesso: " + usuario);
    }

    private void abrirChamado() {
        System.out.println("\n--- Abertura de Chamado ---");

        if (sistema.listarUsuarios().isEmpty()) {
            System.out.println("Não há usuários cadastrados. Cadastre um usuário primeiro.");
            return;
        }

        System.out.println("Usuários disponíveis:");
        for (Usuario usuario : sistema.listarUsuarios()) {
            System.out.println("- ID " + usuario.getId() + " | " + usuario.getNome() + " (" + usuario.getSetor() + ")");
        }

        int idUsuario = lerInteiro("Informe o ID do usuário: ");
        Usuario usuario = sistema.buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        String descricao = lerTexto("Descrição do problema: ");
        Prioridade prioridade = lerPrioridade();
        int tempo = lerInteiro("Tempo estimado de atendimento (min): ");

        Chamado chamado = sistema.abrirChamado(descricao, prioridade, usuario, tempo);
        System.out.println("Chamado aberto com sucesso! ID gerado: " + chamado.getId());
    }

    private void atenderProximoChamado() {
        System.out.println("\n--- Atendimento de Chamado ---");

        List<Tecnico> tecnicos = sistema.listarTecnicos();
        System.out.println("Técnicos disponíveis:");
        for (Tecnico tecnico : tecnicos) {
            System.out.println("- ID " + tecnico.getId() + " | " + tecnico.getNome() + " (" + tecnico.getEspecialidade() + ")");
        }

        int idTecnico = lerInteiro("Informe o ID do técnico responsável: ");
        Chamado chamado = sistema.atenderProximoChamado(idTecnico);
        if (chamado == null) {
            System.out.println("Não há chamados aguardando atendimento.");
            return;
        }

        System.out.println("Chamado atendido e finalizado com sucesso:");
        System.out.println(chamado);
    }

    private void listarFilaChamados() {
        System.out.println("\n--- Chamados em Espera (Fila) ---");
        List<Chamado> fila = sistema.listarChamadosEmEspera();
        if (fila.isEmpty()) {
            System.out.println("Fila vazia.");
            return;
        }

        for (Chamado chamado : fila) {
            System.out.println(chamado);
        }
    }

    private void verHistorico() {
        System.out.println("\n--- Histórico de Chamados (Pilha) ---");
        List<Chamado> historico = sistema.listarHistorico();
        if (historico.isEmpty()) {
            System.out.println("Histórico vazio.");
            return;
        }

        for (Chamado chamado : historico) {
            System.out.println(chamado);
        }
    }

    private void buscarChamadoPorId() {
        System.out.println("\n--- Busca por ID (Árvore Binária) ---");
        int id = lerInteiro("Informe o ID do chamado: ");

        Chamado chamado = sistema.buscarChamadoPorId(id);
        if (chamado == null) {
            System.out.println("Chamado não encontrado.");
            return;
        }

        System.out.println("Chamado encontrado:");
        System.out.println(chamado);
    }

    private void exibirRelatorios() {
        System.out.println("\n--- Relatórios ---");
        System.out.println("Total de chamados em espera: " + sistema.getTotalEmEspera());
        System.out.println("Total de chamados atendidos: " + sistema.getTotalAtendidos());
        System.out.printf("Tempo médio de atendimento (simulado): %.2f min%n", sistema.getTempoMedioAtendimento());

        System.out.println("\nChamados cadastrados em ordem de ID (Árvore em-ordem):");
        for (Chamado chamado : sistema.listarChamadosOrdenadosPorId()) {
            System.out.println(chamado);
        }
    }

    private Prioridade lerPrioridade() {
        while (true) {
            System.out.println("Prioridade: 1-BAIXA | 2-MEDIA | 3-ALTA");
            int op = lerInteiro("Escolha: ");
            switch (op) {
                case 1:
                    return Prioridade.BAIXA;
                case 2:
                    return Prioridade.MEDIA;
                case 3:
                    return Prioridade.ALTA;
                default:
                    System.out.println("Valor inválido para prioridade.");
            }
        }
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String linha = scanner.nextLine();
            try {
                return Integer.parseInt(linha.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Campo não pode ficar vazio.");
        }
    }

    private void pausar() {
        System.out.print("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}
