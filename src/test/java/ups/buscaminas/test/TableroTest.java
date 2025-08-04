package ups.buscaminas.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ups.buscaminas.modelo.Tablero;

// Test para verificar que el número de minas generadas en el tablero coincide con la constante definida.
public class TableroTest {

    @Test
    void minasGeneradasCorrectamente() {
        Tablero t = new Tablero(42);           // semilla fija
        int minas = 0;
        for (int f = 0; f < Tablero.TAM; f++)
            for (int c = 0; c < Tablero.TAM; c++)
                if (t.get(f, c).tieneMina()) minas++;
        assertEquals(Tablero.NUM_MINAS, minas,
            "El número de minas generadas no coincide con la constante.");
    }
}
