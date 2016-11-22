package principal.interfaz_usuario;

import principal.items.equipados.RegistroDeAlmas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JComboBox;

import principal.entes.personajes.Atacable;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.entes.enemigos.Enemigo;
import principal.entes.enemigos.Goblin;
import principal.entes.personajes.Engorgio;
import principal.entes.personajes.Especialidad;
import principal.entes.personajes.Guerrero;
import principal.entes.personajes.Hechicero;
import principal.entes.personajes.Humano;
import principal.entes.personajes.Personaje;

import javax.swing.JProgressBar;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Combate extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnAtacar;
	private JComboBox comboBox;
	private JButton btnSeleccionar;
	private JButton btnHuir;
	private JLabel lblEnergia;
	private JLabel lblSaludTotal;
	private JLabel lblEnergiaTotal;
	private JLabel lblMagiaTotal;
	private JProgressBar barraMagia;
	private JLabel lblMagia;
	private JProgressBar barraSalud;
	private JProgressBar barraEnergia;
	private JLabel lblSalud;
	private boolean turno1;
	private boolean turno2;
	private boolean combatiendo=true;
	
	public boolean isCombatiendo() {
		return combatiendo;
	}

	private boolean huir;
	private Personaje per;
	private Enemigo ene;
	private JLabel imgPersonaje;
	private JLabel imgEnemigo;
	private JLabel nomPersonaje;
	private JLabel nomEnemigo;

	/**
	 * Create the frame.
	 */
	public Combate(Personaje p, Enemigo e) {
		
		this.per = p;
		this.ene = e;
		setBounds(100, 100, 640, 360);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textInactiveText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 197, 624, 125);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnAtacar = new JButton("Atacar");
		btnAtacar.setForeground(Color.BLUE);
		btnAtacar.setFont(new Font("Harrington", Font.PLAIN, 15));
		btnAtacar.setBackground(Color.WHITE);
		btnAtacar.setBounds(10, 11, 89, 23);
		panel.add(btnAtacar);
		
		comboBox = new JComboBox();
		comboBox.setForeground(Color.RED);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Harrington", Font.PLAIN, 15));
		comboBox.setBounds(10, 61, 219, 20);
		panel.add(comboBox);
		
		btnSeleccionar = new JButton("");
		btnSeleccionar.setForeground(Color.BLUE);
		btnSeleccionar.setFont(new Font("Harrington", Font.PLAIN, 15));
		btnSeleccionar.setBounds(10, 92, 219, 23);
		panel.add(btnSeleccionar);
		
		if (p.getCasta().getNombre() != "Hechicero") 
			btnSeleccionar.setText("Seleccionar Habilidad");
		else
			btnSeleccionar.setText("Seleccionar Hechizo");
		
		
		btnHuir = new JButton("Huir");
		btnHuir.setForeground(Color.BLUE);
		btnHuir.setFont(new Font("Harrington", Font.PLAIN, 15));
		btnHuir.setBounds(525, 91, 89, 23);
		panel.add(btnHuir);
		
		barraSalud = new JProgressBar();
		barraSalud.setForeground(Color.GREEN);
		barraSalud.setBounds(348, 11, 192, 14);
		panel.add(barraSalud);
		barraSalud.setMaximum(p.getSaludTotal());
		barraSalud.setMinimum(0);
		barraSalud.setValue(p.getSalud());
		
		barraEnergia = new JProgressBar();
		barraEnergia.setForeground(Color.YELLOW);
		barraEnergia.setBounds(348, 36, 192, 14);
		panel.add(barraEnergia);
		barraEnergia.setMaximum(p.getEnergiaTot());
		barraEnergia.setMinimum(0);
		barraEnergia.setValue(p.getEnergia());
	
		lblSalud = new JLabel("Salud");
		lblSalud.setFont(new Font("Harrington", Font.BOLD, 15));
		lblSalud.setForeground(Color.RED);
		lblSalud.setBounds(287, 11, 51, 14);
		panel.add(lblSalud);
		
		lblEnergia = new JLabel("Energia");
		lblEnergia.setForeground(Color.RED);
		lblEnergia.setFont(new Font("Harrington", Font.BOLD, 15));
		lblEnergia.setBounds(271, 30, 67, 20);
		panel.add(lblEnergia);
		
		lblSaludTotal = new JLabel( p.getSaludTotal() + "/" + p.getSalud());
		lblSaludTotal.setForeground(Color.RED);
		lblSaludTotal.setFont(new Font("Harrington", Font.BOLD, 15));
		lblSaludTotal.setBounds(550, 11, 46, 14);
		panel.add(lblSaludTotal);
		
		lblEnergiaTotal = new JLabel(p.getEnergiaTot() + "/" + p.getEnergia());
		lblEnergiaTotal.setForeground(Color.RED);
		lblEnergiaTotal.setFont(new Font("Harrington", Font.BOLD, 15));
		lblEnergiaTotal.setBounds(550, 36, 46, 14);
		panel.add(lblEnergiaTotal);
		
		imgPersonaje = new JLabel("");
		imgPersonaje.setBounds(65, 61, 125, 125);
		imgPersonaje.setIcon(new ImageIcon(Combate.class.getResource(Constantes.RUTA_PERSONAJE + per.getCasta().getNombre() + "/Combate"  + per.getRaza() + ".png")));
		contentPane.add(imgPersonaje);
		
		
		imgEnemigo = new JLabel("");
		imgEnemigo.setBounds(342, 61, 125, 125);
		imgEnemigo.setIcon(new ImageIcon(Combate.class.getResource(Constantes.RUTA_IMAGEN_DE_COMBATE + "/Combate" + ene.getNombre() + ".png")));
		contentPane.add(imgEnemigo);
		
		nomPersonaje = new JLabel(per.getNombrePersonaje());
		nomPersonaje.setFont(new Font("Harrington", Font.BOLD, 15));
		nomPersonaje.setForeground(Color.BLUE);
		nomPersonaje.setBounds(55, 11, 93, 50);
		contentPane.add(nomPersonaje);
		
		nomEnemigo = new JLabel(ene.getNombre());
		nomEnemigo.setForeground(Color.RED);
		nomEnemigo.setFont(new Font("Harrington", Font.BOLD, 15));
		nomEnemigo.setBounds(394, 11, 139, 50);
		contentPane.add(nomEnemigo);
		
		if (p.getCasta().getNombre() == "Hechicero") {
			
			barraMagia = new JProgressBar();
			barraMagia.setForeground(Color.BLUE);
			barraMagia.setBounds(348, 61, 192, 14);
			panel.add(barraMagia);
			barraMagia.setMaximum(p.getCasta().getMagiaTot());
			barraMagia.setMinimum(0);
			barraMagia.setValue(p.getCasta().getMagia());
			
			lblMagia = new JLabel("Magia");
			lblMagia.setForeground(Color.RED);
			lblMagia.setFont(new Font("Harrington", Font.BOLD, 15));
			lblMagia.setBounds(281, 58, 51, 20);
			panel.add(lblMagia);
			
			lblMagiaTotal = new JLabel(p.getCasta().getMagiaTot() + "/" + p.getCasta().getMagia());
			lblMagiaTotal.setForeground(Color.RED);
			lblMagiaTotal.setFont(new Font("Harrington", Font.BOLD, 15));
			lblMagiaTotal.setBounds(550, 64, 46, 14);
			panel.add(lblMagiaTotal);
		}
		
		for (int i = 0; i < p.getCasta().getHabilidades().size(); i++) {
			comboBox.addItem(p.getCasta().getHabilidades().get(i));
		}
		
		//metodo de los botones//
		
		//presionar atacar//
		btnAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				combatiendo=per.atacar(ene);
				ene.serEnergizado();
				turno2 = true;
				turno1 = false;
					
				lblEnergiaTotal.setText(p.getEnergia()+"/"+p.getEnergiaTot());
				barraEnergia.setValue(p.getEnergia());
				
				if (combatiendo) {
					
					if (ene.puedeAtacar()) {
						System.out.println("El enemigo realiza un ataque");
						//ventanita del enemigo que realiza un ataque//
					}
					else
					{
						System.out.println("El enemigo se canso");
						//ventanita del enemigo se canso
					}
					
					
					btnAtacar.enable(false);
					btnHuir.enable(false);
					btnSeleccionar.enable(false);
					combatiendo=ene.atacar(per);
					
					if (per.estaVivo()) {
						per.serEnergizado();
						barraSalud.setValue(per.getSalud());
						lblSaludTotal.setText(per.getSalud()+ "/" + per.getSaludTotal());
						barraEnergia.setValue(per.getEnergia());
						lblEnergiaTotal.setText(per.getEnergia()+ "/" + per.getEnergiaTot());
					}
					
				}
				
				if (combatiendo==false) {
					
					if (per.estaVivo()) {
						System.out.println("Ganaste");
						//mostrar ventanita de ganaste y salir//
					}
					//else
						//mostrar ventanita perdiste y salir//
				}
			}
		});
		
		//presionar seleccionar habilidad//
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				combatiendo=habilidadesDeCasta();
				ene.serEnergizado();
				turno2 = true;
				turno1 = false;
				
				lblEnergiaTotal.setText(per.getEnergia()+"/"+per.getEnergiaTot());
				barraEnergia.setValue(per.getEnergia());
				
				if (per.getCasta().getNombre() == "Hechicero") {
					barraMagia.setValue(per.getCasta().getMagia());
					lblMagiaTotal.setText(per.getCasta().getMagia() + "/" + per.getCasta().getMagiaTot());
				}
				
				if (combatiendo) {
					
					if (ene.puedeAtacar()) {
						System.out.println("El enemigo realiza un ataque");
						//ventanita del enemigo que realiza un ataque//
					}
					else
					{
						System.out.println("El enemigo se canso");
						//ventanita del enemigo se canso
					}
					
					btnAtacar.enable(false);
					btnHuir.enable(false);
					btnSeleccionar.enable(false);
					combatiendo=ene.atacar(per);
					
					if (per.estaVivo()) {
						per.serEnergizado();
						barraSalud.setValue(per.getSalud());
						lblSaludTotal.setText(per.getSalud()+ "/" + per.getSaludTotal());
						barraEnergia.setValue(per.getEnergia());
						lblEnergiaTotal.setText(per.getEnergia()+ "/" + per.getEnergiaTot());
					}
					
				}
				
				if (combatiendo==false) {
					
					if (per.estaVivo()) {
						System.out.println("Ganaste");
						//mostrar ventanita de ganaste y salir//
					}
					else
					{
						//mostrar ventanita de perdiste y salir//
					}
						
				}
			}
		});
		
		//presionar huir//
		btnHuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				combatiendo=false;
				huir=true;
				
				//mostrar ventanita q dice q huiste pro perdes un item y salir//
			}
		});
	}
			
	public void definirTurnos(){
		
		if (per.getAgilidad() > ene.getAgilidad()){
			turno1= true;
		}
		else{
			turno2=true;
		}
	}

	private boolean habilidadesDeCasta() {
		
		
		switch (per.getCasta().getNombre()) {
		
		case "Hechicero":
		{
			if(per.getCasta().getHechicero().getCantidadDeHechizos() != 0){
				if (!per.getCasta().getHechicero().hechizar((String)comboBox.getSelectedItem(), ene)) {
					//ventanita no se posee la magia para realizar el hechizo//
					System.out.println("No se posee la magia pra hacer el hechizo");
				}
				
				return true;
			}
		}			
				
		case "Guerrero":
		{
			switch((String)comboBox.getSelectedItem()){
			
				case "Posicion Normal":
				{
					per.getCasta().getGuerrero().posicionNormal();break;
				}
					
				case "Posicion Agresiva":
				{
					per.getCasta().getGuerrero().posicionAgresiva();break;
				}
			
				case "Posicion Defensiva":
				{
					per.getCasta().getGuerrero().posicionDefensiva();break;
				}
			
			}
			
			return per.atacar(ene);
		}
		
		case "Ladron":
		{	
			if((String)comboBox.getSelectedItem() == "Daño Critico")
				per.getCasta().getLadron().dañoCritico(per.getDefensa());
			else
				per.getCasta().getLadron().salirDañoCritico();
			
			return per.atacar(ene);
			
		}
		
		
		}
		
		return true;
	}

	public boolean getHuir() {
		
		return this.huir;
	}

	public boolean getTurno1() {
		
		return this.turno1;
	}
	
	public boolean getTurno2() {
		
		return this.turno2;
	}
}
