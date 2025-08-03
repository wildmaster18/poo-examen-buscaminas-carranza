package ups.buscaminas.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ups.buscaminas.modelo.*;

/**
 * Comprueba que, al descubrir todas las celdas no-mina, el juego
 * termine con victoria.
 */
public class JuegoTest {

    @Test
    void victoriaDetectada() throws Exception {
        Juego juego = new Juego();
        Tablero t = juego.getTablero();

        // Descubrir todas las celdas que NO contienen minas
        for (int f = 0; f < Tablero.TAM; f++)
            for (int c = 0; c < Tablero.TAM; c++)
                if (!t.get(f, c).tieneMina())
                    juego.descubrir(new Coordenada(f, c));

        assertTrue(juego.isTerminado(),  "El juego debería estar terminado");
        assertTrue(juego.isVictoria(),   "El jugador debería haber ganado");
    }
}
