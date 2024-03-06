import java.io.IOException;
import java.rmi.RemoteException;

public interface Dicionario extends java.rmi.Remote  {
    public String buscarSignificado(String palavra) throws RemoteException;

    public boolean adicionarPalavra(String palavra, String significado) throws IOException,java.rmi.RemoteException;

    public boolean removerPalavra(String palavra) throws IOException,java.rmi.RemoteException;

    public static void salvarDicionario() throws IOException,java.rmi.RemoteException{}

    public static void carregarDicionario() throws IOException,java.rmi.RemoteException{}
}