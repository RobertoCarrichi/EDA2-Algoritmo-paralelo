import java.io.File;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


public class CifradoDeArchivo{
	String nombre;
	String ruta;
	String archivo;
	int plataforma; //2 : linux
	int key=0;
	File code; //archivo codificado
	File decode;
	
	public void setRutaCompleta(int opc){
		BufferedReader br;
		System.out.print("\n Ingresa la ruta del archivo :\n > ");
		br=new BufferedReader(new InputStreamReader(System.in));
		try{
			this.ruta=br.readLine();
			System.out.print("\n Ingresa el nombre del archivo (con extencion):\n > ");
			this.archivo=br.readLine();
			this.archivo = this.plataforma==1 ? ruta+"\\"+archivo : ruta+"/"+archivo;
			if(opc==1)
				this.code=new File(archivo);
			else
				this.decode=new File(archivo);
		}catch(IOException ioe){}
	}
	
	public void key(){
		Scanner sc=new Scanner(System.in);
		System.out.println(" Llave : ");
		this.key=sc.nextInt();
	}
	
	public void codificar(){
		int i=0;
		char[] car=new char[100];
		int[] rec=new int[100];
		try{
			BufferedReader br=new BufferedReader(new FileReader(archivo));
			this.code= new File(archivo.replaceFirst(".txt","CODIFICADO.txt"));
			code.createNewFile();
			PrintWriter pw=new PrintWriter(code);
			String linea=br.readLine();
			car=linea.toCharArray();
			while(linea!=null&&i<100){
				i=0;
				while((int)car[i]!=0&&i<100&&i<linea.length()-1){
					
					rec[i]=(int)car[i]+this.key;
					car[i]=(char)rec[i];
					i++;
				}
			
				pw.println(String.valueOf(car));
				linea=br.readLine();
				if(linea!=null)
					car=linea.toCharArray();
			}
			br.close();
			pw.close();
			System.out.print("\n Tu archivo se encuentra en :\n > "+this.code.getAbsolutePath()+"\n");
		}catch(FileNotFoundException e){
		}catch(IOException e){}
	}
	
	public void decodificar(){
		int i=0;
		char[] car=new char[100];
		int[] rec=new int[100];
		try{
			BufferedReader br=new BufferedReader(new FileReader(archivo));
			this.decode= new File(archivo.replaceFirst(".txt","DECODIFICADO.txt"));
			decode.createNewFile();
			PrintWriter pw=new PrintWriter(decode);
			String linea=br.readLine();
			car=linea.toCharArray();
			while(linea!=null&&i<100){
				i=0;
				while(car[i]!=0&&i<100&&i<linea.length()-1){
					rec[i]=(int)car[i]-this.key;
					car[i]=(char)rec[i];
					i++;
				}
				pw.println(String.valueOf(car));
				linea=br.readLine();
				if(linea!=null)
					car=linea.toCharArray();
			}
			br.close();
			pw.close();
			System.out.print("\n Tu archivo se encuentra en :\n > "+this.decode.getAbsolutePath()+"\n");
		}catch(FileNotFoundException e){
		}catch(IOException e){}
	}
	
	
}
