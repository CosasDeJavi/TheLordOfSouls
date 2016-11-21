package principal.items.equipados;

import principal.entes.personajes.Personaje;
import principal.entes.personajes.PersonajeEquipado;


public class ConGranAlmaOscura extends PersonajeEquipado {

	public ConGranAlmaOscura(Personaje personajeDecorado) {
		super(2,personajeDecorado);
	}

	@Override
	public int calcularPuntosDeAtaque() {
		return super.calcularPuntosDeAtaque() - 10;
	}
	
}