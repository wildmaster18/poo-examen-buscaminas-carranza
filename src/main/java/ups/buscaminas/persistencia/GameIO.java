package ups.buscaminas.persistencia;

import ups.buscaminas.modelo.Juego;

import java.io.*;
import java.nio.file.*;

// Clase utilitaria para guardar y cargar el estado del juego en disco.
public final class GameIO {

	// Nombre del archivo donde se guarda la partida.
    private static final String FILE_NAME = "buscaminas.save";
    private static final Path FILE_PATH =
            Paths.get(System.getProperty("user.home")).resolve(FILE_NAME);

    // Constructor privado para evitar instanciación.
    private GameIO() { /* util class */ }

    // Guarda el estado del juego en disco.
    public static void guardar(Juego juego) throws IOException {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(Files.newOutputStream(FILE_PATH))) {
            out.writeObject(juego);
        }
    }

    // Carga el estado del juego desde disco.
    public static Juego cargar() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in =
                     new ObjectInputStream(Files.newInputStream(FILE_PATH))) {
            return (Juego) in.readObject();
        }
    }

    // Comprueba si existe una partida guardada.
    public static boolean existePartida() {
        return Files.exists(FILE_PATH);
    }

    // Elimina la partida guardada.
    public static Path getRutaGuardado() { return FILE_PATH; }
}
