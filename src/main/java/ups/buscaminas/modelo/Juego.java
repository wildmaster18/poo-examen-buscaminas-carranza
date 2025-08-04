package ups.buscaminas.modelo;

import java.io.Serializable;
import java.util.Queue;
import java.util.LinkedList;
import ups.buscaminas.modelo.excepciones.*;

public class Juego implements Serializable {
    private static final long serialVersionUID = 1L;

    // Atributos principales
    private final Tablero tablero = new Tablero();
    private boolean terminado, victoria;

    // Getters principales
    public Tablero getTablero()  { return tablero; }
    public boolean isTerminado() { return terminado; }
    public boolean isVictoria()  { return victoria; }



    // Estado inicial
    public int getBanderasColocadas() {
        int n = 0;
        for (int f = 0; f < Tablero.TAM; f++)
            for (int c = 0; c < Tablero.TAM; c++)
                if (tablero.get(f,c).isMarcada()) n++;
        return n;
    }

    // Minas restantes (no marcadas)
    public int getMinasRestantes() {
        return Tablero.NUM_MINAS - getBanderasColocadas();
    }

    // Acciones del jugador

    public void marcar(Coordenada c) throws CoordenadaInvalidaException {
        if (!tablero.enRango(c.fila(),c.columna())) throw new CoordenadaInvalidaException();
        tablero.get(c.fila(),c.columna()).marcar();
    }

    // Descubre la celda en la coordenada dada
    public void descubrir(Coordenada c)
            throws CoordenadaInvalidaException, CeldaYaDescubiertaException {
        
    	// Validar que la coordenada esté dentro del rango del tablero
    	if (!tablero.enRango(c.fila(),c.columna())) throw new CoordenadaInvalidaException();
        Celda celda = tablero.get(c.fila(),c.columna());
        
        // Validar que la celda no haya sido descubierta previamente
        if (celda.isDescubierta()) throw new CeldaYaDescubiertaException();

        // Descubrir la celda
        celda.descubrir();
        if (celda.tieneMina()) { terminado = true; victoria = false; return; }

        // Si la celda es un número y no tiene minas alrededor, aplicar flood fill
        if (celda instanceof CeldaNumero cn && cn.getMinasAlrededor()==0)
            floodFill(c);

        comprobarVictoria();
    }


    // Métodos auxiliares
    private void floodFill(Coordenada origen){
        Queue<Coordenada> q=new LinkedList<>();
        q.add(origen);
        while(!q.isEmpty()){
            Coordenada p=q.remove();
            for(int df=-1;df<=1;df++)
                for(int dc=-1;dc<=1;dc++){
                    int nf=p.fila()+df, nc=p.columna()+dc;
                    if(tablero.enRango(nf,nc)){
                        Celda v=tablero.get(nf,nc);
                        if(!v.isDescubierta() && !v.tieneMina()){
                            v.descubrir();
                            if(v instanceof CeldaNumero cn && cn.getMinasAlrededor()==0)
                                q.add(new Coordenada(nf,nc));
                        }
                    }
                }
        }
    }

    // Comprueba si se ha alcanzado la condición de victoria
    private void comprobarVictoria(){
        for(int f=0;f<Tablero.TAM;f++)
            for(int c=0;c<Tablero.TAM;c++)
                if(!tablero.get(f,c).tieneMina() && !tablero.get(f,c).isDescubierta())
                    return;
        terminado=true; victoria=true;
    }
}
