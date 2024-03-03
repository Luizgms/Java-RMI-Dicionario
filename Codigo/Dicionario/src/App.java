
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String host = args[0];
        int porta = Integer.parseInt(args[1]);

        Registry registry = LocateRegistry.getRegistry(host, porta);
        Dicionario dicionario = (Dicionario) registry.lookup("Dicionario");

    }
}