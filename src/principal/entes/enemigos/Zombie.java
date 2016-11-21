package principal.entes.enemigos;

public class Zombie extends Enemigo {

int ataquesRecibidos;
	
	public Zombie(){
	
	this.nombre= "Zombie";
	this.energiaTot= 20;
	this.energia= energiaTot;
	this.salud= 100;
	this.experiencia= 20;
	this.idAlma= 2;
	this.agilidad = 3;
	this.fatiga = 15;
	this.recuperacion = 5;
	
	}

	@Override
	protected int calcularPuntosDeAtaque() {
		return 10 + ataquesRecibidos;
	}

	@Override
	public void serAtacado(int dano) {
		super.serAtacado(dano);
		this.ataquesRecibidos++;
	}

}
