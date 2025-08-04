package ups.buscaminas.modelo;

/** Celda que contiene una mina. */
public class CeldaMina extends Celda {
    private static final long serialVersionUID = 1L;

    // Constructor que inicializa una celda de mina no descubierta y no marcada
    @Override public boolean tieneMina() { return true; }

    // Retorna el símbolo que representa el estado de la celda de mina
    @Override public char simbolo() {
        if (!descubierta) return marcada ? 'F' : '#';
        return 'X';
    }
}
