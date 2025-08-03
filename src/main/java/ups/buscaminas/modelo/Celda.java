package ups.buscaminas.modelo;

import java.io.Serializable;

/** Clase base para todas las celdas del tablero. */
public abstract class Celda implements Serializable {
    private static final long serialVersionUID = 1L;
    protected boolean descubierta;
    protected boolean marcada;

    public boolean isDescubierta() { return descubierta; }
    public boolean isMarcada()     { return marcada;     }

    public void marcar()    { marcada = !marcada; }
    public void descubrir() { descubierta = true; }

    public abstract boolean tieneMina();
    public abstract char simbolo();
}
