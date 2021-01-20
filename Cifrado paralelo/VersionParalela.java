import java.io.File;
import java.lang.InterruptedException;

public class VersionParalela {
	public static void main(String[] args) throws InterruptedException{



        /*
			Se indica el archivo a cifrar.
        */
		File file = new File("test.txt"); 

		/*
			Se debe indicar si quiere cifrarse el archivo o descifrarse.
			Tipos de modo:
				0 -> Para CIFRAR
				1 -> Para DESCIFRAR
		*/
		int modo = 1;
        
		/*
			Se debe verificar si el archivo existe para que el proceso pueda continuar.
		*/
		if (file.exists()) {
			/*
				#######################################
				#	COMIENZA EL PROCESO DE CIFRADO    #
				#######################################


				LEER ARCHIVO
				DIVIDIR POR LA MITAD
					IMPRIMIR MITADES EN AUXILIARES
				CADA UNO DE LOS HILOS DEBE 
					LEER SU RESPECTIVO AUXILIAR
					CIFRARLO
					IMPRIMIRLO EN EL MISMO AUXILIAR

				HASTA QUE HAYAN TERMINADO AMBOS ARCHIVOS DE CIFRAR E IMPRIMIR
				REPETIR PARA CADA HILO
					LEER UN AUXILIAR, IMPRIMIRLO EN EL ARCHIVO FINAL 
				TERMINA	
			*/
	         
			/*
				SE LEE EL ARCHIVO A CIFRAR.
			*/
		    long inicio = 0;
		    long fin = 0;

		    if (modo == 0) {
				System.out.println("\n\tComienza el cifrado!");
		    	inicio = System.currentTimeMillis();
		    	
	        	fin = System.currentTimeMillis();
				System.out.println("\tTermina el cifrado!");
		    }else if (modo == 1) {
				System.out.println("\n\tComienza el descifrado!");
		    	inicio = System.currentTimeMillis();

	        	fin = System.currentTimeMillis();
				System.out.println("\tTermina el descifrado!");
		    }else{
		    	System.out.println("OH NO! Ha ocurrido un error con el modo de operacion.");
		    }

		    if (inicio != 0) {
		        double tiempo = (double) ((fin - inicio)/1000);
		        System.out.println("\n\tEl proceso a tardado "+tiempo +" segundos.");
		    }
        }else {
        	System.out.println("CHISPAS! El archivo con el que se quiere trabajar no existe.\n");
		}
	}
}