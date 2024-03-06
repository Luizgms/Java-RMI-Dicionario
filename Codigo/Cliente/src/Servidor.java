import java.io.IOException;
import java.rmi.Naming;

public class Servidor {
	public Servidor() {
		try {
			Dicionario c = new DicionarioImp();
			Naming.rebind("rmi://localhost/DicionarioService", c);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String args[]) throws IOException {
		new Servidor();
		System.out.println("Servidor Calculadora em execução.");
	}
}
