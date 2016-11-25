package principal.peticiones;

public class CodigoPeticion {
		public static final int CONEXION_CON_SERVIDOR = 1000;

		
		public static final int LOGUEO = 1;
		public static final int LOGUEO_INCORRECTO = 10;
		public static final int LOGUEO_CORRECTO = 11;
		
		public static final int REGISTRO = 2;	
		public static final int REGISTRO_CORRECTO = 21;
		public static final int REGISTRO_INCORRECTO_USER_YA_EXISTE = 200;
		public static final int REGISTRO_INCORRECTO = 201;
				
		public static final int REGISTRO_PERSONAJE = 3;	
		public static final int REGISTRO_PERSONAJE_CORRECTO = 31;
		public static final int REGISTRO_PERSONAJE_INCORRECTO= 30;
		public static final int REGISTRO_PERSONAJE_YA_EXISTE= 32;
		
		public static final int LISTAR_RAZAS = 4;	
		public static final int LISTADO_RAZAS_CORRECTO = 41;
		public static final int LISTADO_RAZAS_INCORRECTO = 40;
		
		public static final int LISTAR_CASTAS = 5;	
		public static final int LISTADO_CASTAS_CORRECTO = 51;
		public static final int LISTADO_CASTAS_INCORRECTO = 50;
		
		public static final int PONER_EN_MAPA_JUGADOR = 6;
		public static final int PONER_EN_MAPA_JUGADOR_CORRECTO = 61;
		public static final int PONER_EN_MAPA_JUGADOR_INCORRECTO = 60;
				
		public static final int AGREGAR_PERSONAJE = 7;
		public static final int AGREGAR_PERSONAJE_CORRECTO = 71;
		public static final int AGREGAR_PERSONAJE_INCORRECTO = 70;
		
		public static final int LEVANTAR_MAPA = 8;
		public static final int LEVANTAR_MAPA_CORRECTO = 81;
		public static final int LEVANTAR_MAPA_INCORRECTO = 80;
}
