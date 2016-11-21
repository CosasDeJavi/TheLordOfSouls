package principal.herramientas;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CargadorRecursos {

	public static BufferedImage cargarImagenCompatibleOpaca(final String ruta){
		Image imagen = null;
		
		try{
				imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
			
		}catch (IOException e){
			e.printStackTrace();
		}
		
		GraphicsConfiguration gc= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		
		BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null), Transparency.OPAQUE);
		
		Graphics g= imagenAcelerada.getGraphics();
		g.drawImage(imagen, 0, 0, null);
		g.dispose();
		
		return imagenAcelerada;
	}
	
	public static BufferedImage cargarImagenCompatibleTranslucida(final String ruta){
		Image imagen = null;
		
		try{
				imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
			
		}catch (IOException e){
			e.printStackTrace();
		}
		
		GraphicsConfiguration gc= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		
		BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null),Transparency.TRANSLUCENT);
		
		Graphics g= imagenAcelerada.getGraphics();
		g.drawImage(imagen, 0, 0, null);
		g.dispose();
		
		return imagenAcelerada;
	}
}
