import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Dicionario {

    private Map<String, String> dicionario;

    public Dicionario() {
        dicionario = new HashMap<>();
        carregarDicionario();
    }

    public String buscarSignificado(String palavra) {
        return dicionario.get(palavra);
    }

    public boolean adicionarPalavra(String palavra, String significado) {
        if (dicionario.containsKey(palavra)) {
            return false;
        }
        dicionario.put(palavra, significado);
        salvarDicionario();
        return true;
    }

    public boolean removerPalavra(String palavra) {
        if (!dicionario.containsKey(palavra)) {
            return false;
        }
        dicionario.remove(palavra);
        salvarDicionario();
        return true;
    }

    private void carregarDicionario() {

        try (FileReader reader = new FileReader("dicionario.json")) {
            String json = "";
            int ch;
            while ((ch = reader.read()) != -1) {
                json += (char) ch;
            }

            // Converter a string JSON para um objeto `Map`
            ObjectMapper mapper = new ObjectMapper();
            dicionario = mapper.readValue(json, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
            // Exceção lançada caso o arquivo JSON não seja encontrado ou não possa ser lido
            throw new RuntimeException("Erro ao carregar dicionário: " + e.getMessage());
        }
    }

    private void salvarDicionario() {
        // Abrir o arquivo JSON para escrita
        try (FileWriter writer = new FileWriter("dicionario.json")) {
            // Converter o objeto `Map` para uma string JSON
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(dicionario);

            // Escrever a string JSON no arquivo
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
            // Exceção lançada caso o arquivo JSON não possa ser criado ou escrito
            throw new RuntimeException("Erro ao salvar dicionário: " + e.getMessage());
        }
    }
}