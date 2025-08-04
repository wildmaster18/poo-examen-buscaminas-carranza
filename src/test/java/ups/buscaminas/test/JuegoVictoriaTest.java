package ups.buscaminas.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ups.buscaminas.modelo.*;

/**
 * Comprueba que, al descubrir todas las celdas no-mina,
 * el juego termina con victoria.
 */
public class JuegoVictoriaTest {

    @Test
    void descubreTodasLasCasillasSegurasYGana() throws Exception {
        Juego juego = new Juego();
        Tablero t   = juego.getTablero();

        for (int f = 0; f < Tablero.TAM; f++) {
            for (int c = 0; c < Tablero.TAM; c++) {
                if (!t.get(f, c).tieneMina() && !t.get(f, c).isDescubierta()) {
                    juego.descubrir(new Coordenada(f, c));
                }
            }
        }

        assertTrue(juego.isTerminado(), "El juego debería estar terminado");
        assertTrue(juego.isVictoria(),  "El jugador debería haber ganado");
    }
}
