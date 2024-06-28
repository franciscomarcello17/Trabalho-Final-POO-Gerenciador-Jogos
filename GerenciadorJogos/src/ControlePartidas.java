import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ControlePartidas {

    // mapas para armazenar jogos e jogadore
    private Map<Integer, Jogo> jogos;
    private Map<Integer, Jogador> jogadores;
    private List<Partida> partidas; // lista para armazenar partidas

    // construtor para inicializar os mapas e a lista
    public ControlePartidas() {
        jogos = new HashMap<>();
        jogadores = new HashMap<>();
        partidas = new ArrayList<>();
    }

    // metodo para adicionar um jogo ao mapa de jogos
    public void adicionarJogo(int id, String nome, String categoria) {
        Jogo jogo = new Jogo(id, nome, categoria);
        jogos.put(id, jogo);
    }

    // metodo para adicionar jogador ao mapa de jogadors
    public void adicionarJogador(int id, String nome) {
        Jogador jogador = new Jogador(id, nome);
        jogadores.put(id, jogador);
    }

    // metodo para adicionar partida a lista de partidas
    public void adicionarPartida(int id, int idJogo, int idJogador1, int idJogador2, String resultado) {
        Jogo jogo = jogos.get(idJogo); // obtem o jogo pelo id
        Jogador jogador1 = jogadores.get(idJogador1); // obtem o jogador 1 pelo id
        Jogador jogador2 = jogadores.get(idJogador2); // obtem o jogador 2 pelo id

        // jogo ou jogadores existem
        if (jogo == null || jogador1 == null || jogador2 == null) {
            System.out.println("Erro ao adicionar partida: jogo ou jogadores nao encontrado.");
            return;
        }

        // cria e adiciona partida
        Partida partida = new Partida(id, jogo, jogador1, jogador2, resultado);
        partidas.add(partida);
    }

    // metodo para remover um jogo
    public void removerJogo(int id) {
        if (jogos.containsKey(id)) {
            jogos.remove(id);
            System.out.println("Jogo removido com sucesso.");
        } else {
            System.out.println("Jogo nao encontrado.");
        }
    }

    // metodo para remover um jogador
    public void removerJogador(int id) {
        if (jogadores.containsKey(id)) {
            jogadores.remove(id);
            System.out.println("Jogador removido com sucesso.");
        } else {
            System.out.println("Jogador nao encontrado.");
        }
    }

    // metodo para remover uma partida
    public void removerPartida(int id) {
        Partida partidaRemover = null;
        for (Partida partida : partidas) {
            if (partida.id == id) {
                partidaRemover = partida;
                break;
            }
        }
        if (partidaRemover != null) {
            partidas.remove(partidaRemover);
            System.out.println("Partida removida com sucesso.");
        } else {
            System.out.println("Partida nao encontrada.");
        }
    }

    // metodo para listar jgoos
    public void listarJogos() {
        System.out.println("Lista de Jogos:");
        for (Jogo jogo : jogos.values()) {
            System.out.println(jogo);
        }
    }

    // metodo para listar jogadores
    public void listarJogadores() {
        System.out.println("Lista de Jogadores:");
        for (Jogador jogador : jogadores.values()) {
            System.out.println(jogador);
        }
    }

    // metodo para listar partidas
    public void listarPartidas() {
        System.out.println("Lista de Partidas:");
        for (Partida partida : partidas) {
            System.out.println(partida);
        }
    }

    // metodo para exportar dados para txt
    public void exportarParaTXT(String nomeArquivo, String caminhoDestino) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoDestino + "/" + nomeArquivo))) {
            writer.write("Jogos:\n");
            for (Jogo jogo : jogos.values()) {
                writer.write(jogo.toString() + "\n");
            }
            writer.write("\nJogadores:\n");
            for (Jogador jogador : jogadores.values()) {
                writer.write(jogador.toString() + "\n");
            }
            writer.write("\nPartidas:\n");
            for (Partida partida : partidas) {
                writer.write(partida.toString() + "\n");
            }
            System.out.println("Exportacao para " + caminhoDestino + "/" + nomeArquivo + " concluida com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao exportar para " + caminhoDestino + "/" + nomeArquivo + ": " + e.getMessage());
        }
    }

    // exibir o menu
    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int escolha;
        do {
            System.out.println("\n### Menu ###");
            System.out.println("1. Adicionar Jogo");
            System.out.println("2. Remover Jogo");
            System.out.println("3. Listar Jogos");
            System.out.println("4. Adicionar Jogador");
            System.out.println("5. Remover Jogador");
            System.out.println("6. Listar Jogadores");
            System.out.println("7. Adicionar Partida");
            System.out.println("8. Remover Partida");
            System.out.println("9. Listar Partidas");
            System.out.println("10. Exportar Dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");

            escolha = scanner.nextInt();
            scanner.nextLine();

            // switch case pro menu
            switch (escolha) {
                case 1:
                    System.out.print("ID do jogo: ");
                    int idJogo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome do jogo: ");
                    String nomeJogo = scanner.nextLine();
                    System.out.print("Categoria do jogo: ");
                    String categoriaJogo = scanner.nextLine();
                    adicionarJogo(idJogo, nomeJogo, categoriaJogo);
                    break;
                case 2:
                    System.out.print("Digite o ID do jogo a remover: ");
                    int idRemoverJogo = scanner.nextInt();
                    removerJogo(idRemoverJogo);
                    break;
                case 3:
                    listarJogos();
                    break;
                case 4:
                    System.out.print("ID do jogador: ");
                    int idJogador = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome do jogador: ");
                    String nomeJogador = scanner.nextLine();
                    adicionarJogador(idJogador, nomeJogador);
                    break;
                case 5:
                    System.out.print("Digite o ID do jogador a remover: ");
                    int idRemoverJogador = scanner.nextInt();
                    removerJogador(idRemoverJogador);
                    break;
                case 6:
                    listarJogadores();
                    break;
                case 7:
                    System.out.print("ID da partida: ");
                    int idPartida = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ID do jogo: ");
                    int idJogoPartida = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ID do jogador 1: ");
                    int idJogador1 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ID do jogador 2: ");
                    int idJogador2 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Resultado da partida: ");
                    String resultadoPartida = scanner.nextLine();
                    adicionarPartida(idPartida, idJogoPartida, idJogador1, idJogador2, resultadoPartida);
                    break;
                case 8:
                    System.out.print("Digite o ID da partida a remover: ");
                    int idRemoverPartida = scanner.nextInt();
                    removerPartida(idRemoverPartida);
                    break;
                case 9:
                    listarPartidas();
                    break;
                case 10:
                    System.out.print("Digite o nome do arquivo para exportar (ex: dados.txt): ");
                    String nomeArquivo = scanner.nextLine();
                    System.out.print("Digite o caminho completo do diretorio de destino: ");
                    String caminhoDestino = scanner.nextLine();
                    exportarParaTXT(nomeArquivo, caminhoDestino);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (escolha != 0);

        scanner.close();
    }

    // classe interna pra representar um jogo
    private static class Jogo {
        private int id;
        private String nome;
        private String categoria;

        public Jogo(int id, String nome, String categoria) {
            this.id = id;
            this.nome = nome;
            this.categoria = categoria;
        }
 
        @Override
        public String toString() {
            return "ID: " + id + ", Nome: " + nome + ", Categoria: " + categoria;
        }
    }

    // classe interna pra representar um jogador
    private static class Jogador {
        private int id;
        private String nome;

        public Jogador(int id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Nome: " + nome;
        }
    }

    // classe interna pra representar uma partida
    private static class Partida {
        private int id;
        private Jogo jogo;
        private Jogador jogador1;
        private Jogador jogador2;
        private String resultado;

        public Partida(int id, Jogo jogo, Jogador jogador1, Jogador jogador2, String resultado) {
            this.id = id;
            this.jogo = jogo;
            this.jogador1 = jogador1;
            this.jogador2 = jogador2;
            this.resultado = resultado;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Jogo: " + jogo.nome + ", Jogador 1: " + jogador1.nome +
                    ", Jogador 2: " + jogador2.nome + ", Resultado: " + resultado;
        }
    }

    // metodo main pra iniciar o programa
    public static void main(String[] args) {
        ControlePartidas controle = new ControlePartidas();
        controle.exibirMenu();
    }
}
