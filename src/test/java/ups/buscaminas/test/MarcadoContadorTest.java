package ups.buscaminas.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ups.buscaminas.modelo.*;

class MarcadoContadorTest {

    @Test
    void marcarYDesmarcarActualizaContador() throws Exception {
        Juego juego = new Juego();

        // Selecciona una coordenada cualquiera (A1)
        Coordenada a1 = new Coordenada(0,0);

        int inicial = juego.getMinasRestantes();
        juego.marcar(a1);
        assertEquals(inicial - 1, juego.getMinasRestantes(),
            "Al marcar, minas restantes debe decrementar");

        juego.marcar(a1); // desmarca
        assertEquals(inicial, juego.getMinasRestantes(),
            "Al desmarcar, contador vuelve al valor inicial");
    }
}
