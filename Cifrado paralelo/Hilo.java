import java.io.File;
import java.util.ArrayList;

/* 
 * 
 */
public class Hilo extends Thread{

    String nombre;
    int desplazamiento;
    char[] texto;
    String textoEncriptado = "";

    /*
     *
     */
    public Hilo (String nombre, char[] texto, int desplazamiento) {
        this.nombre = nombre;
        this.texto = texto;
        this.desplazamiento = desplazamiento;
    }

    /*
     *  
     * 
     * @Override
     */
    public void run(){
        /*
            COMIENZA EL PROCESO DE ENCRIPTACIÓN
        */
        final int LONGITUD_ALFABETO = 26;
        final int INICIO_MINUSCULAS = 97;
        final int INICIO_MAYUSCULAS = 65;

        System.out.println("\n\tEl hilo " + this.nombre + " ha comenzado!");
        for (Character letra : this.texto) {

            if ( ! Character.isLetter(letra) ) {
                this.textoEncriptado += letra;
                continue;
            }
            
            int ascii = (int) letra;
            int nuevaPosicionEnAlfabeto = ( ( ascii - ( Character.isUpperCase(letra) ? INICIO_MAYUSCULAS : INICIO_MINUSCULAS) ) + desplazamiento) % LONGITUD_ALFABETO;
            if ( nuevaPosicionEnAlfabeto < 0 ) {
                nuevaPosicionEnAlfabeto += LONGITUD_ALFABETO;
            }
            int nuevaPosicionAscii = (Character.isUpperCase(letra) ? INICIO_MAYUSCULAS : INICIO_MINUSCULAS) + nuevaPosicionEnAlfabeto;
            this.textoEncriptado += (char) nuevaPosicionAscii;
        }
        int size = this.textoEncriptado.length();
        while ( (int) this.textoEncriptado.charAt(size - 1) == 0 ){
            this.textoEncriptado = this.textoEncriptado.substring(0, size - 1);
            size--;
        }
        System.out.println("\n\tEl hilo "+this.nombre+" ha terminado!");
    }
}