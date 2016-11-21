package principal.graficos;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class Ventana extends JFrame implements WindowFocusListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5979421777239930009L;

	private String titulo;
	
	public Ventana(String titulo, SuperFicieDibujo sd){
		this.titulo = titulo;
		
		configurarVentana(sd);
	}

	private void configurarVentana(final SuperFicieDibujo sd) {

		setTitle(titulo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setResizable(false);
		//setIconImage(image);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(sd, BorderLayout.CENTER);
		//setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		requestFocus();
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		setFocusable(true);
		requestFocus();
		
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		setFocusable(true);
		requestFocus();
		
	}
}
