package principal.cs;		
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;

import principal.Constantes;
import principal.GestorPrincipal;
import principal.peticiones.*;

public class ServerThread extends Thread{
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Server sv;	

	public ServerThread(Socket s, Server sv) {
		super();
		this.sv = sv;
		this.s = s;
		try {
			this.oos = new ObjectOutputStream(s.getOutputStream());
		} catch (Exception e) {
			System.out.println("Problema generando el OOS");
		}
		try {
			this.ois = new ObjectInputStream(s.getInputStream());
		} catch (Exception e) {
			System.out.println("Problema generando el OIS");
		}
	}

	@Override
	public void run() {
		while(true){
			Mensaje mjeIn = null;
			try {
				mjeIn = (Mensaje) ois.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("El thread del sv no encontró la clase Mensaje al hacer readObject");
			} catch (IOException e) {
				System.out.println("IOException en el thread del server al recibir un mensaje");
				e.printStackTrace();
			}
			switch (mjeIn.getCodigo()){
			case CodigoPeticion.LOGUEO:
				PeticionLogueo petLog = (PeticionLogueo) mjeIn.getObj();
				try {
					oos.writeObject(new Mensaje(sv.getConexionBD().login(petLog.getUsuario(), new String(petLog.getPassword())),null));		//manda mje con el código que devuelva el intento de login en la BD
					oos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case CodigoPeticion.REGISTRO:
				PeticionRegistro petReg = (PeticionRegistro) mjeIn.getObj();
				try {
					oos.writeObject(new Mensaje(sv.getConexionBD().registro(petReg.getUsuario(), new String(petReg.getPassword()), petReg.getEmail()),null));
					oos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case CodigoPeticion.PONER_EN_MAPA_JUGADOR:
				int aux = this.sv.getListaUsuariosMapa1().size();
				this.sv.agregarAListaDeMapa1(oos);
				try {
					if(this.sv.getListaUsuariosMapa1().size() == aux+1)
						oos.writeObject(new Mensaje(CodigoPeticion.PONER_EN_MAPA_JUGADOR_CORRECTO,null));
					else
						oos.writeObject(new Mensaje(CodigoPeticion.PONER_EN_MAPA_JUGADOR_INCORRECTO,null));
					oos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				/*
				break;
			 	case CodigoPeticion.LEVANTAR_MAPA:
				PeticionLevantarMapa petMap = (PeticionLevantarMapa) mjeIn.getObj();
				try {
					try{
						GestorPrincipal gp=new GestorPrincipal(petMap.getTitulo(),petMap.getAncho(), petMap.getAlto());
						gp.iniciarJuego();
						gp.iniciarBuclePrincipal();
						oos.writeObject(new Mensaje(CodigoPeticion.LEVANTAR_MAPA_CORRECTO ,null));
					}catch(Exception e){
						oos.writeObject(new Mensaje(CodigoPeticion.LEVANTAR_MAPA_INCORRECTO  ,null));
					}
					oos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				break;
				
			case CodigoPeticion.REGISTRO_PERSONAJE:
				PeticionCrearPersonaje petCrearPersonaje = (PeticionCrearPersonaje) mjeIn.getObj();
				try {
					oos.writeObject(new Mensaje(
							sv.getConexionBD().registroPersonaje(petCrearPersonaje.getId_usuario(), petCrearPersonaje.getNombrePer(),petCrearPersonaje.getRaza(),petCrearPersonaje.getCasta(),petCrearPersonaje.getSexo()),null));
					oos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				break;
			case CodigoPeticion.LISTAR_RAZAS:
				try{
					//ArrayList<String> listaRazas = sv.getConexionBD().listarRazas();
					Map<Integer, String> razasMap = sv.getConexionBD().listarRazas();
				
					//if(!listaRazas.isEmpty())
					if(!razasMap.isEmpty())
					{
					Mensaje mensaje = new Mensaje(CodigoPeticion.LISTAR_RAZAS,razasMap);
					oos.writeObject(mensaje);
					oos.flush();
					}
					else{
						JOptionPane.showMessageDialog(null, "No se puede cargar las razas");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case CodigoPeticion.LISTAR_CASTAS:
				try{
					//ArrayList<String> listaCastas = sv.getConexionBD().listarCastas();
					Map<Integer, String> castasMap = sv.getConexionBD().listarCastas();
					if(!castasMap.isEmpty())
					{
					Mensaje mensaje = new Mensaje(CodigoPeticion.LISTAR_CASTAS,castasMap);
					
					oos.writeObject(mensaje);
					oos.flush();
					}
					else{
						JOptionPane.showMessageDialog(null, "No se puede cargar las Castas");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
		
}