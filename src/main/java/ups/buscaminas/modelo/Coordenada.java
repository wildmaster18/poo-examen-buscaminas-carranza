package ups.buscaminas.modelo;

// Clase inmutable que representa una coordenada en el tablero del juego de Buscaminas
public record Coordenada(int fila, int columna) implements java.io.Serializable {

    // Constructor que valida las coordenadas
    public static Coordenada parse(String s) {
        s = s.trim().toUpperCase();
        if (!s.matches("[A-J](10|[1-9])"))
            throw new IllegalArgumentException("Formato válido: A1-J10");
        return new Coordenada(s.charAt(0) - 'A',
                              Integer.parseInt(s.substring(1)) - 1);
    }
}
