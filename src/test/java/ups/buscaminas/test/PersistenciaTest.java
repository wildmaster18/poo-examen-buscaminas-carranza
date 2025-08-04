package ups.buscaminas.test;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Files;
import org.junit.jupiter.api.Test;
import ups.buscaminas.modelo.*;
import ups.buscaminas.persistencia.GameIO;

class PersistenciaTest {

    @Test
    void guardarYCargarRestauranEstado() throws Exception {
        Juego original = new Juego();
        // Marca una celda para tener estado distinto
        original.marcar(new Coordenada(0,0));

        // Guarda
        GameIO.guardar(original);

        // Carga
        Juego cargado = GameIO.cargar();

        // Mismos flags y estado
        assertEquals(original.getMinasRestantes(), cargado.getMinasRestantes());
        assertEquals(original.isTerminado(),      cargado.isTerminado());
        assertEquals(original.isVictoria(),       cargado.isVictoria());

        // Limpieza: elimina archivo
        Files.deleteIfExists(GameIO.getRutaGuardado());
    }
}
