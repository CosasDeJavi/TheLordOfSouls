package principal.cs;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.LinkedList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import baseDeDatos.SQLiteConnection;

public class Server extends Thread{
	static int PUERTO_POR_DEFECTO = 50000;
	private LinkedList<ObjectOutputStream> listaUsuariosMapa1;
	private LinkedList<ObjectOutputStream> listaUsuariosMapa2;
	private ServerSocket svSocket;
	private SQLiteConnection conexionBD;
	

	public LinkedList<ObjectOutputStream> getListaUsuariosMapa1() {
		return listaUsuariosMapa1;
	}
	
	public LinkedList<ObjectOutputStream> getListaUsuariosMapa2() {
		return listaUsuariosMapa2;
	}
	
	public SQLiteConnection getConexionBD() {
		return conexionBD;
	}

	public Server() {
		try {
			svSocket = new ServerSocket(PUERTO_POR_DEFECTO);
		} catch (Exception e) {
			// TODO: handle exception
		}
		conexionBD = new SQLiteConnection();
		listaUsuariosMapa1 = new LinkedList<ObjectOutputStream>();
		listaUsuariosMapa2 = new LinkedList<ObjectOutputStream>();
	}

	public void run(){	// esto queda corriendo atendiendo las conexiones nuevas y creando nuevos threads por c/u
		Socket s = null;
		while(true){
			try {
				s = this.svSocket.accept();
			} catch (Exception e) {
				System.out.println("Hubo un problema con la atención de un pedido de conexión");
			}
			new ServerThread(s,this).start();
		}
	}
	

	public static void main(String[] args) {
		new Server().start();
	}
}

