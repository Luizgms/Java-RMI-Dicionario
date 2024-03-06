import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DicionarioImp extends java.rmi.server.UnicastRemoteObject implements Dicionario{

    private static Map<String, String> dicionario;

    public DicionarioImp() throws IOException,java.rmi.RemoteException {
        dicionario = new HashMap<>();
        carregarDicionario();
    }

    public String buscarSignificado(String palavra) throws java.rmi.RemoteException {
        return dicionario.get(palavra);
    }

    public boolean adicionarPalavra(String palavra, String significado) throws IOException,java.rmi.RemoteException {
        if (dicionario.containsKey(palavra)) {
            return false;
        }
        dicionario.put(palavra, significado);
        salvarDicionario();
        return true;
    }

    public boolean removerPalavra(String palavra) throws IOException,java.rmi.RemoteException {
        if (!dicionario.containsKey(palavra)) {
            return false;
        }
        dicionario.remove(palavra);
        salvarDicionario();
        return true;
    }

    public static void salvarDicionario() throws IOException,java.rmi.RemoteException {

        BufferedWriter escritor = new BufferedWriter(new FileWriter("dicionario.txt"));

        try {
            for (Map.Entry<String, String> entrada : dicionario.entrySet()) {
                escritor.write(entrada.getKey() + " : " + entrada.getValue());
                escritor.newLine();
            }
        } finally {
            escritor.close();
        }
    }

    public static void carregarDicionario() throws IOException,java.rmi.RemoteException {

        BufferedReader leitor = new BufferedReader(new FileReader("dicionario.txt"));

        try {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(":");

                dicionario.put(partes[0], partes[1]);
            }
        } finally {
            leitor.close();
        }
    }
}