import java.io.File;

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
	public Cifrado (File archivo, int desplazamiento) {
		/*
			SE LEE EL ARCHIVO A CIFRAR.
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
	public static void cifrar() {
		/*
			#######################################
			#	COMIENZA EL PROCESO DE CIFRADO    #
			#######################################
		*/

		/*
			SE DIVIDE EL ARCHIVO.
				IMPRIMIR MITADES EN AUXILIARES
		*/

		/*
			COMIENZA EL PROCESO DE EJECUCIÓN PARA CADA UNO DE LOS HILOS

			CADA UNO DE LOS HILOS DEBE 
				LEER SU RESPECTIVO AUXILIAR
				CIFRARLO
				IMPRIMIRLO EN EL MISMO AUXILIAR
		*/

		/*
			SE REPORTAN LOS RESULTADOS. SOLO DESPUÉS DE QUE AMBOS HILOS HAN TERMINADO.

			HASTA QUE HAYAN TERMINADO AMBOS ARCHIVOS DE CIFRAR E IMPRIMIR
			REPETIR PARA CADA HILO
				LEER UN AUXILIAR, IMPRIMIRLO EN EL ARCHIVO FINAL 
			TERMINA	
		*/


		// return rotar(archivo, 3);
	}

	/*
	 * Este método realizará el proceso inverso para volver al contenido del archivo original.
	 * Leyendo el resultado de ambos archivos auxiliares, descifrar el contenido en hilos diferentes
	 * e imprimirlo.
	 *
	 */
	public static void descifrar() {
		/*
			Se deberá descifrar a partir del mismo desplazamiento, pero en un sentido negativo.
		*/
		// return rotar(archivo, -3);
	}
}