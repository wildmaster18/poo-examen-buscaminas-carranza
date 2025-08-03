package ups.buscaminas.modelo;

/** Celda que contiene una mina. */
public class CeldaMina extends Celda {
    private static final long serialVersionUID = 1L;

    @Override public boolean tieneMina() { return true; }

    @Override public char simbolo() {
        if (!descubierta) return marcada ? 'F' : '#';
        return 'X';
    }
}
