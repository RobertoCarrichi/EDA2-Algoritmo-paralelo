import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/*
 * Clase inicial encarga de ejecutar cualquier proceso en el archivo.
 */
public class VersionParalela {
	public static void main(String[] args) throws IOException, FileNotFoundException{
        /*
			Se indica el archivo a cifrar o descifrar.
        */
		File file = new File("short.txt"); 

		/*
			Se debe indicar si quiere cifrarse el archivo o descifrarse.
			Tipos de modo:
				0 -> Para CIFRAR
				1 -> Para DESCIFRAR
		*/
		int modo = 0;

		/*
			Se debe indicar la cantidad de veces a desplazar el abecedario.
		*/
        int desplazamiento = 3;

        /*
			Numero de hilos
        */
		int hilos = 2;

		/*
			Se debe verificar si el archivo existe para que el proceso pueda continuar.
		*/
		if (file.exists()) {
			/*
				Se genera la clase encargada de aplicar cualquier proceso en el archivo.
			*/
			Cifrado cifrado = (modo == 0 ? new Cifrado(file, desplazamiento) : new Cifrado(file, - desplazamiento)); 

			/*
				Son definidos los tiempos iniciales de ejecución.
			*/
		    long inicio = 0;
		    long fin = 0;

		    if ( (modo == 0) && (file.length() != 0) ) {
				System.out.println("\n\tComienza el cifrado!");
		    	inicio = System.currentTimeMillis();
		    	cifrado.cifrar();
	        	fin = System.currentTimeMillis();
				System.out.println("\n\tTermina el cifrado!");

		    }else if ((modo == 1)  && (file.length() != 0) ) {
				System.out.println("\n\tComienza el descifrado!");
		    	inicio = System.currentTimeMillis();
		    	cifrado.cifrar();
	        	fin = System.currentTimeMillis();
				System.out.println("\n\tTermina el descifrado!");

		    }else{
		    	if (file.length() == 0) {
					System.out.println("El archivo que se encuentra vacio.");		    		
		    	} else {
		    		System.out.println("OH NO! Ha ocurrido un error con el modo de operacion.");
		    	}
		    }

		    if (inicio != 0) {
		        double tiempo = (fin - inicio) / 1000;
		        System.out.println("\n\tEl proceso a tardado "+tiempo +" segundos.");
		    }
        }else {
        	System.out.println("CHISPAS! El archivo con el que se quiere trabajar no existe.\n");
		}
	}
}