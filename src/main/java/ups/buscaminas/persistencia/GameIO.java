package ups.buscaminas.persistencia;

import java.io.*;
import ups.buscaminas.modelo.Juego;

/** Persistencia binaria de la partida. */
public final class GameIO {
    private static final String FILE = "partida.dat";
    private GameIO() {}

    public static void guardar(Juego juego) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(juego);
        }
    }

    public static Juego cargar() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            return (Juego) ois.readObject();
        }
    }

    public static boolean existePartida() { return new File(FILE).exists(); }
}
