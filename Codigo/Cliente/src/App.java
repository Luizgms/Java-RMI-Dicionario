import java.io.IOException;
import java.util.Scanner;

public class App {

    private static Dicionario dicionario;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        dicionario = new Dicionario();

        // Menu principal
        while (true) {
            int opcao = menu();

            switch (opcao) {
                case 1:
                    buscarPalavra();
                    break;
                case 2:
                    adicionarPalavra();
                    break;
                case 3:
                    removerPalavra();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static int menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Dicionário ---");
        System.out.println("1. Buscar Palavra");
        System.out.println("2. Adicionar Palavra");
        System.out.println("3. Remover Palavra");
        System.out.println("4. Sair");
        System.out.print("Digite a opção desejada: ");
        return scanner.nextInt();
    }

    private static void buscarPalavra() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a palavra que deseja buscar: ");
        String palavra = scanner.nextLine();

        String significado = dicionario.buscarSignificado(palavra);
        if (significado == null) {
            System.out.println("Palavra não encontrada!");
        } else {
            System.out.println("Significado: " + significado);
        }
    }

    private static void adicionarPalavra() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a palavra que deseja adicionar: ");
        String palavra = scanner.nextLine();

        System.out.print("Digite o significado da palavra: ");
        String significado = scanner.nextLine();

        if (dicionario.adicionarPalavra(palavra, significado)) {
            System.out.println("Palavra adicionada com sucesso!");
        } else {
            System.out.println("Palavra já existe no dicionário!");
        }
    }

    private static void removerPalavra() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a palavra que deseja remover: ");
        String palavra = scanner.nextLine();

        if (dicionario.removerPalavra(palavra)) {
            System.out.println("Palavra removida com sucesso!");
        } else {
            System.out.println("Palavra não encontrada no dicionário!");
        }
    }
}