package ups.buscaminas.modelo;

import java.io.Serializable;
import ups.buscaminas.modelo.excepciones.*;

public class Juego implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Tablero tablero = new Tablero();
    private boolean terminado, victoria;

    public Tablero getTablero()  { return tablero; }
    public boolean isTerminado() { return terminado; }
    public boolean isVictoria()  { return victoria; }

    public void marcar(Coordenada c) throws CoordenadaInvalidaException {
        if (!tablero.enRango(c.fila(), c.columna())) throw new CoordenadaInvalidaException();
        tablero.get(c.fila(), c.columna()).marcar();
    }

    public void descubrir(Coordenada c)
            throws CoordenadaInvalidaException, CeldaYaDescubiertaException {
        if (!tablero.enRango(c.fila(), c.columna())) throw new CoordenadaInvalidaException();
        Celda celda = tablero.get(c.fila(), c.columna());
        if (celda.isDescubierta()) throw new CeldaYaDescubiertaException();

        celda.descubrir();
        if (celda.tieneMina()) { terminado = true; victoria = false; return; }

        if (celda instanceof CeldaNumero cn && cn.getMinasAlrededor()==0)
            floodFill(c);

        comprobarVictoria();
    }

    private void floodFill(Coordenada origen) {
        java.util.Queue<Coordenada> q = new java.util.LinkedList<>();
        q.add(origen);
        while (!q.isEmpty()) {
            Coordenada p = q.remove();
            for (int df=-1; df<=1; df++)
                for (int dc=-1; dc<=1; dc++) {
                    int nf = p.fila()+df, nc = p.columna()+dc;
                    if (tablero.enRango(nf,nc)) {
                        Celda v = tablero.get(nf,nc);
                        if (!v.isDescubierta() && !v.tieneMina()) {
                            v.descubrir();
                            if (v instanceof CeldaNumero cn && cn.getMinasAlrededor()==0)
                                q.add(new Coordenada(nf,nc));
                        }
                    }
                }
        }
    }

    private void comprobarVictoria() {
        for (int f=0; f<Tablero.TAM; f++)
            for (int c=0; c<Tablero.TAM; c++)
                if (!tablero.get(f,c).tieneMina() && !tablero.get(f,c).isDescubierta())
                    return;
        terminado = true; victoria = true;
    }
}
