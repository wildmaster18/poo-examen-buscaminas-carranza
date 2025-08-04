package ups.buscaminas.modelo;

import java.io.Serializable;

// Clase abstracta que representa una celda en el tablero del juego de Buscaminas
public abstract class Celda implements Serializable {
    private static final long serialVersionUID = 1L;
    protected boolean descubierta;
    protected boolean marcada;

    // Constructor que inicializa una celda no descubierta y no marcada
    public boolean isDescubierta() { return descubierta; }
    
    // Retorna true si la celda ha sido descubierta
    public boolean isMarcada()     { return marcada;     }

    // Métodos para marcar y descubrir la celda
    public void marcar()    { marcada = !marcada; }
    
    // Marca o desmarca la celda
    public void descubrir() { descubierta = true; }
    
    // Métodos abstractos que deben ser implementados por las subclases
    public abstract boolean tieneMina();
    
    // Retorna el símbolo que representa el estado de la celda
    public abstract char simbolo();
}
