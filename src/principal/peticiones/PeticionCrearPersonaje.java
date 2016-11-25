package principal.peticiones;

import java.io.Serializable;

import principal.cs.ServerThread;

public class PeticionCrearPersonaje implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_usuario;
	private String nombrePer;
	private int raza;
	private int casta;
	private char sexo;
	
	public PeticionCrearPersonaje(int id_usuario, String nombrePer, int Raza, int Casta, char sexo) {
		this.id_usuario = id_usuario;
		this.raza=raza;
		this.casta=casta;
		this.nombrePer=nombrePer;
		this.sexo=sexo;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public String getNombrePer() {
		return nombrePer;
	}

	public int getRaza() {
		return raza;
	}

	public int getCasta() {
		return casta;
	}

	public char getSexo() {
		return sexo;
	}
	
}