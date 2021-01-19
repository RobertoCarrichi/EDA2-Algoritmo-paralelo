import java.util.Scanner;

public class CifradoSecuencial{
	public static void main(String[] args){
		int opc;
		Scanner sc=new Scanner(System.in);
		CifradoDeArchivo cifrado=new CifradoDeArchivo();
		System.out.println(" (De)codificacion de archivos");
		do{
			System.out.print("\n Seelcciona el tipo de plataforma :\n 1) Windows\n 2) UNIX\n > ");
			cifrado.plataforma=sc.nextInt();
		}while(cifrado.plataforma!=1&&cifrado.plataforma!=2);
		do{
			
			System.out.println(" Selecciona una opcion:\n 1) Codificar\n 2) Decodificar\n 3) Salir\n > ");
			opc=sc.nextInt();
			if(opc==2||opc==1){
				cifrado.setRutaCompleta(opc);
				cifrado.key();
			}
			switch(opc){
				case 1: cifrado.codificar();
						break;
				case 2: cifrado.decodificar();
						break;
				case 3:break;
				default: System.out.println(" Opcion incorrecta\n");
			}
		}while(opc!=3);		
	}
}
