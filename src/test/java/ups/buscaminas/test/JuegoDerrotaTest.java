package ups.buscaminas.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ups.buscaminas.modelo.*;

class JuegoDerrotaTest {

    /** Revelar intencionadamente una mina debe terminar la partida en derrota. */
    @Test
    void derrotaDetectada() throws Exception {
        Juego juego = new Juego();
        Tablero t = juego.getTablero();

        // Buscar una mina y descubrirla
        outer:
        for (int f = 0; f < Tablero.TAM; f++)
            for (int c = 0; c < Tablero.TAM; c++)
                if (t.get(f, c).tieneMina()) {
                    juego.descubrir(new Coordenada(f, c));
                    break outer;
                }

        assertTrue(juego.isTerminado(), "El juego debería estar terminado");
        assertFalse(juego.isVictoria(), "No debería ser victoria");
    }
}
