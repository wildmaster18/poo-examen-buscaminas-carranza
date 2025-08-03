package ups.buscaminas.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ups.buscaminas.modelo.Tablero;

/**
 * Verifica que el tablero contenga exactamente la cantidad de minas
 * especificada por la constante NUM_MINAS.
 */
public class TableroTest {

    @Test
    void minasGeneradasCorrectamente() {
        Tablero t = new Tablero(42);  // semilla fija para reproducibilidad
        int minas = 0;
        for (int f = 0; f < Tablero.TAM; f++)
            for (int c = 0; c < Tablero.TAM; c++)
                if (t.get(f, c).tieneMina())
                    minas++;
        assertEquals(Tablero.NUM_MINAS, minas);
    }
}
