package principal.interfaz_usuario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Constantes;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class SeleccionarPersonaje extends JFrame implements ItemListener{

	private JPanel contentPane;
	private JComboBox comboBoxPersonajes;
	private JButton btnCargar;
	private JLabel lblNombre;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private String nombrePersonaje="MiPersonaje"; //esta variable deberia tomar el nombre del personaje desde la base de datos//
	private String mapa="Mapa"; //esta variable deberia tomar el nombre del mapa desde la base de datos//
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionarPersonaje frame = new SeleccionarPersonaje();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	/**
	 * Create the frame.
	 */
	public SeleccionarPersonaje() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBoxPersonajes = new JComboBox();
		comboBoxPersonajes.setFont(new Font("Harrington", Font.PLAIN, 15));
		//aca Carga las partidas//
		comboBoxPersonajes.setModel(new DefaultComboBoxModel(new String[] {"Sin datos", "MiPartida"}));
		comboBoxPersonajes.setBackground(Color.WHITE);
		comboBoxPersonajes.setForeground(Color.RED);
		comboBoxPersonajes.setBounds(189, 68, 165, 23);
		comboBoxPersonajes.addItemListener(this); //indispensable para que detecte que se cambia el valor de comboBox
		contentPane.add(comboBoxPersonajes);
		
		btnCargar = new JButton("Cargar");
		btnCargar.setFont(new Font("Harrington", Font.PLAIN, 15));
		btnCargar.setBackground(Color.WHITE);
		btnCargar.setForeground(Color.BLUE);
		btnCargar.setBounds(175, 202, 89, 23);
		contentPane.add(btnCargar);
		
		lblNombre = new JLabel("NombrePersonaje:");
		lblNombre.setForeground(Color.BLUE);
		lblNombre.setFont(new Font("Harrington", Font.BOLD, 15));
		lblNombre.setVerticalAlignment(SwingConstants.TOP);
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setBackground(Color.WHITE);
		lblNombre.setBounds(41, 70, 138, 20);
		contentPane.add(lblNombre);
		
		lblNewLabel_1 = new JLabel("Selecciona el personaje a cargar");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 23));
		lblNewLabel_1.setBounds(41, 21, 363, 30);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SeleccionarPersonaje.class.getResource(Constantes.RUTA_SELECCION_PERSONAJE)));
		lblNewLabel.setBounds(0, 0, 434, 262);
		contentPane.add(lblNewLabel);
		
	}

	@Override
	public void itemStateChanged(ItemEvent seleccion) {
		/*
		 if (seleccion.getSource()==comboBoxPersonajes) {
			 	
			 if ((String)comboBoxPersonajes.getSelectedItem() == "MiPartida") {
				 lblSinDatos_1.setText(nombrePersonaje);
		         lblSinDatos_2.setText(mapa);
			}
			 else
			 {
				 	lblSinDatos_1.setText("Sin Datos");
		            lblSinDatos_2.setText("Sin Datos");
			 } 
	     }
		 */
	}
}
