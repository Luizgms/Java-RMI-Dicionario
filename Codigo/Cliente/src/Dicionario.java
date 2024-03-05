import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Dicionario {

    private static Map<String, String> dicionario;

    public Dicionario() throws IOException {
        dicionario = new HashMap<>();
        carregarDicionario();
    }

    public String buscarSignificado(String palavra) {
        return dicionario.get(palavra);
    }

    public boolean adicionarPalavra(String palavra, String significado) throws IOException {
        if (dicionario.containsKey(palavra)) {
            return false;
        }
        dicionario.put(palavra, significado);
        salvarDicionario();
        return true;
    }

    public boolean removerPalavra(String palavra) throws IOException {
        if (!dicionario.containsKey(palavra)) {
            return false;
        }
        dicionario.remove(palavra);
        salvarDicionario();
        return true;
    }

    public static void salvarDicionario() throws IOException {

        // Criar um BufferedWriter para escrever no arquivo
        BufferedWriter escritor = new BufferedWriter(new FileWriter("dicionario.txt"));

        try {
            // Escrever cada entrada do dicionário no arquivo
            for (Map.Entry<String, String> entrada : dicionario.entrySet()) {
                escritor.write(entrada.getKey() + " : " + entrada.getValue());
                escritor.newLine();
            }
        } finally {
            // Fechar o BufferedWriter
            escritor.close();
        }
    }

    public static void carregarDicionario() throws IOException {

        // Criar um BufferedReader para ler do arquivo
        BufferedReader leitor = new BufferedReader(new FileReader("dicionario.txt"));

        try {
            // Ler cada linha do arquivo e adicionar ao dicionário
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(":");

                dicionario.put(partes[0], partes[1]);
            }
        } finally {
            // Fechar o BufferedReader
            leitor.close();
        }
    }
}