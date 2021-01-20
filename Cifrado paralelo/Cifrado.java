import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


/* 
 * 
 */
public class Cifrado {

	File archivo;
	int desplazamiento;
	int hilos;
	/*
	 *
	 *
	 *	
	 */
	public Cifrado (int hilos, File archivo, int desplazamiento) throws FileNotFoundException, IOException {
		/*
			Se inicializa el archivo con el que estará trabajando y el desplazamiento que existirá
			para 
		*/
		this.hilos = hilos;
		this.archivo = archivo;
		this.desplazamiento = desplazamiento;
	}

	/*
	 * Este es el método que manejará el proceso indicado para leer el contenido de un archivo,
	 * partirlo en dos archivos auxiliares y cifrar cada archivo paralelamente, al termino de esto
	 * se busca unir el contenido de ambos archivos en un archivo final.
	 *
	 */
	public void cifrar() throws IOException, FileNotFoundException{
		/*
			#######################################
			#	COMIENZA EL PROCESO DE CIFRADO    #
			#######################################
		*/

		FileReader fr = new FileReader(this.archivo);
		BufferedReader buffer = new BufferedReader(fr);

		Hilo[] hilos = new Hilo[this.hilos];
		int tamanio = (int) ( (this.archivo.length() / this.hilos) + 1);
		char[] texto = new char[tamanio];
		try {
			for (int i = 0 ; i < hilos.length ; i ++ ) {
				buffer.read(texto, 0, tamanio);
				hilos[i] = new Hilo(""+(i+1), texto, this.desplazamiento);
				hilos[i].start();
				texto = new char[tamanio];
			}


			/*
				Ya que ha terminado el encriptado del texto se procede a unir ambas mitades para
				crear el archivo final.	
			*/
			File resultado = (desplazamiento > 0 ? new File("encriptado.txt") : new File("desencriptado.txt"));
			PrintWriter writer = new PrintWriter(resultado);
			
			for (int i = 0 ; i < hilos.length ; i++ ) {
				hilos[i].join();
				writer.append(hilos[i].textoEncriptado);
			}
			writer.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}