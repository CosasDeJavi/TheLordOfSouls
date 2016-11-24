package principal.cs;

import java.io.*;

public class ClienThread extends Thread{

	ObjectInputStream ois;
	Mensaje mje = null;
	
	public ClienThread(ObjectInputStream ois){
		super();
		this.ois = ois;
	}
	
	public void run(){
		while(true){
			try {
				this.mje = (Mensaje) ois.readObject();
				} catch (Exception e) {
						try {
							ois.close();
							break;
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							System.out.println("No se pudo cerrar el OIS.");
						}
			}
		}
	}
	
	public Mensaje getMensaje(){
		while(this.mje == null){
			
		}
		Mensaje recibido = this.mje;
		this.mje = null;
		return recibido;
	}
}