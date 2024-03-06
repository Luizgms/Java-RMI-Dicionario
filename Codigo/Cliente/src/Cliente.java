import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String servidor = "rmi://localhost/";
        String nome = "DicionarioService";

        try {
            Dicionario dicionario = (Dicionario) Naming.lookup(servidor + nome);
            System.out.println("Objeto remoto \'" + nome + "\' encontrado no servidor.");

            while (true) {
                int opcao = menu();

                switch (opcao) {
                    case 1:
                        buscarPalavra(dicionario);
                        break;
                    case 2:
                        adicionarPalavra(dicionario);
                        break;
                    case 3:
                        removerPalavra(dicionario);
                        break;
                    case 4:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (MalformedURLException e) {
            System.out.println("URL \'" + servidor + nome + "\' mal formatada.");
        } catch (RemoteException e) {
            System.out.println("Erro na invocacaçãoo remota.");
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.out.println("Objeto remoto \'" + nome + "\' n�o est� dispon�vel.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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

    private static void buscarPalavra(Dicionario dicionario) throws RemoteException {
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

    private static void adicionarPalavra(Dicionario dicionario) throws IOException {
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

    private static void removerPalavra(Dicionario dicionario) throws IOException {
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