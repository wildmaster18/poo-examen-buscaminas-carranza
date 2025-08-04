package ups.buscaminas.modelo;

public class CeldaNumero extends Celda {
    private static final long serialVersionUID = 1L;
    private int minasAlrededor;

    // Constructor que inicializa una celda numérica no descubierta y no marcada
    public void setMinasAlrededor(int n) { minasAlrededor = n; }
    
    // Establece el número de minas alrededor de esta celda
    public int  getMinasAlrededor()      { return minasAlrededor; }

    // Retorna false ya que esta celda no contiene una mina
    @Override public boolean tieneMina() { return false; }

    // Retorna el símbolo que representa el estado de la celda numérica
    @Override public char simbolo() {
        if (!descubierta) return marcada ? 'F' : '#';
        return minasAlrededor == 0 ? 'V' : (char) ('0' + minasAlrededor);
    }
}
