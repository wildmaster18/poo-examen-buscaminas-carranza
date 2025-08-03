package ups.buscaminas.modelo;

/** Value-object inmutable (fila, columna) 0-based. */
public record Coordenada(int fila, int columna) implements java.io.Serializable {

    /** Convierte una cadena tipo “A5” en Coordenada(0,4). */
    public static Coordenada parse(String s) {
        s = s.trim().toUpperCase();
        if (!s.matches("[A-J](10|[1-9])"))
            throw new IllegalArgumentException("Formato válido: A1-J10");
        return new Coordenada(s.charAt(0) - 'A',
                              Integer.parseInt(s.substring(1)) - 1);
    }
}
