package ups.buscaminas.modelo;

public class CeldaNumero extends Celda {
    private static final long serialVersionUID = 1L;
    private int minasAlrededor;

    public void setMinasAlrededor(int n) { minasAlrededor = n; }
    public int  getMinasAlrededor()      { return minasAlrededor; }

    @Override public boolean tieneMina() { return false; }

    @Override public char simbolo() {
        if (!descubierta) return marcada ? 'F' : '#';
        return minasAlrededor == 0 ? 'V' : (char) ('0' + minasAlrededor);
    }
}
