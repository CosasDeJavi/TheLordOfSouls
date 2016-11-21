package principal.entes.enemigos;

public class RegistroEnemigos {
    public static Enemigo obtenerEnemigo(final int idEnemigo) {
        Enemigo enemigo = null;

        switch (idEnemigo) {
            case 1:
                enemigo = new Goblin();
                break;
        }

        return enemigo;
    }
}