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

		ArrayList<Character> mitad1 = new ArrayList<Character>();
		ArrayList<Character> mitad2 = new ArrayList<Character>();
		
		boolean iniciado = false;

		/*
			Se definen los hilos que trabajarán con la información del archivo.
		*/
		Hilo hilo1 = null;
		Hilo hilo2 = null;

		/*
			SE DIVIDE EL ARCHIVO.
		*/
		for (int i = 1; i <= this.archivo.length() ; i++ ) {
			if (i < tamanio) {
				mitad1.add( (char) buffer.read() );
			} else if ( i >= tamanio ) {
				mitad2.add( (char) buffer.read() );
			}
			if ( i >= tamanio && ! iniciado ) {
				/*
					Si la primera mitad ya alcanzado su capacidad de caracteres máxima, ya puede
					comenzar a cifrarse la primera parte del archivo.
				*/
				iniciado = true;
				hilo1 = new Hilo("A", mitad1, this.desplazamiento);
				hilo1.start();
			}
		}

		/*
			La única forma de llegar a este punto es cuando se alcanzó el final del archivo, por lo que
			la segunda mitad ya se encuentra lista para cifrarse, así que comienza la ejecución del segundo
			hilo.
		*/
		hilo2 = new Hilo("B", mitad2, this.desplazamiento);
		hilo2.start();

		try {
			hilo1.join();
			hilo2.join();
			/*
				Ya que ha terminado el encriptado del texto se procede a unir ambas mitades para
				crear el archivo final.	
			*/
			File resultado = new File("resultado-encriptado.txt");
			PrintWriter writer = new PrintWriter(resultado);
			writer.append(hilo1.textoEncriptado);
			writer.append(hilo2.textoEncriptado);
			writer.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Este método realizará el proceso inverso para volver al contenido del archivo original.
	 * Leyendo el resultado de ambos archivos auxiliares, descifrar el contenido en hilos diferentes
	 * e imprimirlo.
	 *
	 */
	public void descifrar() throws FileNotFoundException, IOException {
		/*
			Se deberá descifrar a partir del mismo desplazamiento, pero en un sentido negativo.
		*/
		/*
			#######################################
			#	COMIENZA EL PROCESO DE CIFRADO    #
			#######################################
		*/

		FileReader fr = new FileReader(this.archivo);
		BufferedReader buffer = new BufferedReader(fr);
		
		// Se determina el máximo de caracteres con el que trabajará cada hilo.
		int tamanio = (int) ( (this.archivo.length() / 2) + 1);

		ArrayList<Character> mitad1 = new ArrayList<Character>();
		ArrayList<Character> mitad2 = new ArrayList<Character>();
		
		boolean iniciado = false;

		/*
			Se definen los hilos que trabajarán con la información del archivo.
		*/
		Hilo hilo1 = null;
		Hilo hilo2 = null;

		/*
			SE DIVIDE EL ARCHIVO.
		*/
		for (int i = 1; i <= this.archivo.length() ; i++ ) {
			if (i < tamanio) {
				mitad1.add( (char) buffer.read() );
			} else if ( i >= tamanio ) {
				mitad2.add( (char) buffer.read() );
			}
			if ( i >= tamanio && ! iniciado ) {
				/*
					Si la primera mitad ya alcanzado su capacidad de caracteres máxima, ya puede
					comenzar a cifrarse la primera parte del archivo.
				*/
				iniciado = true;
				hilo1 = new Hilo("A", mitad1, - this.desplazamiento);
				hilo1.start();
			}
		}

		/*
			La única forma de llegar a este punto es cuando se alcanzó el final del archivo, por lo que
			la segunda mitad ya se encuentra lista para cifrarse, así que comienza la ejecución del segundo
			hilo.
		*/
		hilo2 = new Hilo("B", mitad2, - this.desplazamiento);
		hilo2.start();

		try {
			hilo1.join();
			hilo2.join();
			/*
				Ya que ha terminado el encriptado del texto se procede a unir ambas mitades para
				crear el archivo final.	
			*/
			File resultado = new File("resultado-desencriptado.txt");
			PrintWriter writer = new PrintWriter(resultado);
			writer.append(hilo1.textoEncriptado);
			writer.append(hilo2.textoEncriptado);
			writer.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}