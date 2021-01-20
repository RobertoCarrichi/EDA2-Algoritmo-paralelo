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
	
	/*
	 *
	 *
	 *	
	 */
	public Cifrado (File archivo, int desplazamiento) throws FileNotFoundException, IOException {
		/*
			Se inicializa el archivo con el que estará trabajando y el desplazamiento que existirá
			para 
		*/
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
		
		// Se determina el máximo de caracteres con el que trabajará cada hilo.
		int tamanio = (int) ( (this.archivo.length() / 2) + 1);

		char[] mitad1 = new char[tamanio];

		/*
			Se definen los hilos que trabajarán con la información del archivo.
		*/
		Hilo hilo1 = null;
		Hilo hilo2 = null;

		/*
			SE DIVIDE EL ARCHIVO.
		*/
		buffer.read(mitad1,0, (int) ((this.archivo.length() / 2) + 1));

		/*
			Si la primera mitad ya alcanzado su capacidad de caracteres máxima, ya puede
			comenzar a cifrarse la primera parte del archivo.
		*/
		hilo1 = new Hilo("A", mitad1, this.desplazamiento);
		hilo1.start();

		/*
			Se realiza la lectura de datos del siguiente bloque de texto que reste en el buffer,
			para esto se reinicializa el arreglo de caracteres.
		*/
		mitad1 = new char[tamanio];
		buffer.read(mitad1, 0, (int) ( (this.archivo.length() / 2) + 1));
		hilo2 = new Hilo("B", mitad1, this.desplazamiento);
		hilo2.start();
		
		try {
			hilo1.join();
			hilo2.join();
			/*
				Ya que ha terminado el encriptado del texto se procede a unir ambas mitades para
				crear el archivo final.	
			*/
			File resultado = (desplazamiento > 0 ? new File("encriptado.txt") : new File("desencriptado.txt"));
			PrintWriter writer = new PrintWriter(resultado);
			writer.append(hilo1.textoEncriptado);
			writer.append(hilo2.textoEncriptado);
			writer.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}