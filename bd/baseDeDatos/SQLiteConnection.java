package baseDeDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import principal.entes.personajes.Personaje;
import principal.peticiones.CodigoPeticion;

public class SQLiteConnection {
	private static Connection conn = null;
	
	public SQLiteConnection () {
		conn = SQLiteConnection.dbConnector();
	}
		
	public static Connection dbConnector(){ //va a devolver la conexión
		conn = null;
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:.\\bd\\baseDeDatos\\BDPrueba.sqlite");
			//BORRAR
			JOptionPane.showMessageDialog(null,"Conexión exitosa"); //se supone que debería mostrar el error
			conn.setAutoCommit(true);
			//BORRAR
			return conn;
		}catch(Exception e){
			 JOptionPane.showMessageDialog(null,e); //se supone que debería mostrar el error
			 return null;
		}
	}
	
	public int login(String usuario, String password) {
		PreparedStatement pst = null;		
		try{
			String query = "select * from Usuario where nombre = ? and password = ?";
			pst= conn.prepareStatement(query); 
			pst.setString(1, usuario); //0 es el primer ? que pongo en la query
			pst.setString(2, (String) password); //0 es el primer ? que pongo en la query
			
			ResultSet rs = pst.executeQuery();
			if (rs.next()){
				//return CodigoPeticion.LOGEO_CORRECTO;
				return rs.getInt("ID");
			}
			else {
				 return CodigoPeticion.LOGUEO_INCORRECTO;
			}
		}catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return CodigoPeticion.LOGUEO_INCORRECTO;
	}
	
	public int registro(String usuario, String password, String mail){
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		try{
			String query = "select * from Usuario where nombre = ?";
			pst= conn.prepareStatement(query); 
	
			pst.setString(1, usuario);
			ResultSet rs = pst.executeQuery();
			if (rs.next()){
				return CodigoPeticion.REGISTRO_INCORRECTO_USER_YA_EXISTE;
			}
			
			String query2 = "insert into Usuario (nombre, mail, password) values (?,?,?)";
			pst2= conn.prepareStatement(query2); 
			pst2.setString(1, usuario); //1 es el primer ? que pongo en la query
			pst2.setString(2, mail);
			pst2.setString(3, password);
			
			pst2.executeUpdate();		//notar que para hacer cambios en vez de consultas se usa executeUpdate()
			return CodigoPeticion.REGISTRO_CORRECTO;
		}catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return CodigoPeticion.REGISTRO_INCORRECTO;
	}
	
	public int registroPersonaje(int id_usuario, String nombrePer, int raza, int casta, char sexo) {
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		try{
			String query = "select * from Personaje where id_usuario = ? and nombre = ?";
			pst= conn.prepareStatement(query); 
			pst.setInt(1, id_usuario);
			pst.setString(2, nombrePer);
			ResultSet rs = pst.executeQuery();
			if (rs.next()){
				return CodigoPeticion.REGISTRO_PERSONAJE_YA_EXISTE;
			}
			
			String query2 = "insert into Personaje (id_usuario,nombre, raza, casta, sexo) values (?,?,?,?,?)";
			pst2= conn.prepareStatement(query2); 
			pst2.setInt(1, id_usuario);
			pst2.setString(2, nombrePer);
			pst2.setInt(3, raza);
			pst2.setInt(4, casta);
			pst2.setLong(5,sexo);
			
			pst2.executeUpdate();		//notar que para hacer cambios en vez de consultas se usa executeUpdate()
			return CodigoPeticion.REGISTRO_PERSONAJE_CORRECTO;
		}catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return CodigoPeticion.REGISTRO_PERSONAJE_INCORRECTO;
	}
	
	public Map<Integer, String> listarRazas(){
		PreparedStatement pst = null;
		try{
			String query = "SELECT * FROM Raza";
			pst= conn.prepareStatement(query); 
			Map <Integer,String> razasMap = new HashMap<Integer,String>();
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				razasMap.put(rs.getInt("ID_RAZA"), rs.getString("DESCRIPCION"));
			}
			return razasMap;
		}catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Map<Integer, String> listarCastas(){
		PreparedStatement pst = null;
		try{
			String query = "SELECT * FROM casta";
			pst= conn.prepareStatement(query); 
			Map <Integer,String> castasMap = new HashMap<Integer,String>();
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				castasMap.put(rs.getInt("ID_CASTA"), rs.getString("DESCRIPCION"));
			}
			return castasMap;
		}catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}