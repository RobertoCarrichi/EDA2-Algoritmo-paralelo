import java.io.File;

public class VersionParalela {
	public static void main(String[] args) {


		File file = new File("test.txt");
		String mensajeOriginal = "Hola mundo abcdefghijklmnopqrstuvwxyzñ";
		String mensajeCifrado = cifrar(mensajeOriginal);
		String mensajeDescifrado = descifrar(mensajeCifrado);
		System.out.printf("Original: %s\nCifrado: %s\nDescrifrado: %s\n", mensajeOriginal, mensajeCifrado, mensajeDescifrado);

	}
}