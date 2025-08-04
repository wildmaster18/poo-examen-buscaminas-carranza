package ups.buscaminas.modelo;

import java.io.Serializable;
import java.util.Random;

// Clase que representa el tablero del juego de Buscaminas
public class Tablero implements Serializable {
    private static final long serialVersionUID = 1L;

    /// Tamaño del tablero (10x10).
    public static final int TAM = 10;

    /// Número de minas en el tablero.
    public static final int NUM_MINAS = 10;

    /// Matriz de celdas que conforman el tablero.
    private final Celda[][] celdas = new Celda[TAM][TAM];

    // Constructor que inicializa el tablero con una semilla aleatoria
    public Tablero() { this(System.currentTimeMillis()); }

    public Tablero(long seed) {
        generarVacias();
        colocarMinas(seed);
        calcularNumeros();
    }

    // Métodos para acceder y manipular las celdas del tablero
    public Celda get(int f, int c)              { return celdas[f][c]; }
    public boolean enRango(int f,int c)         { return f>=0&&f<TAM && c>=0&&c<TAM; }
    public int contarMinasAlrededor(int f,int c){
        int t=0;
        for(int df=-1;df<=1;df++)
            for(int dc=-1;dc<=1;dc++)
                if(!(df==0&&dc==0)&&enRango(f+df,c+dc)&&celdas[f+df][c+dc].tieneMina()) t++;
        return t;
    }

    // Genera un tablero vacío sin minas
    private void generarVacias(){
        for(int f=0;f<TAM;f++)
            for(int c=0;c<TAM;c++)
                celdas[f][c]=new CeldaNumero();
    }

    // Coloca minas en el tablero de forma aleatoria usando la semilla dada
    private void colocarMinas(long seed){
        Random rnd=new Random(seed);
        int colocadas=0;
        while(colocadas<NUM_MINAS){
            int f=rnd.nextInt(TAM), c=rnd.nextInt(TAM);
            if(!celdas[f][c].tieneMina()){
                celdas[f][c]=new CeldaMina();
                colocadas++;
            }
        }
    }

    // Calcula el número de minas alrededor de cada celda numérica
    private void calcularNumeros(){
        for(int f=0;f<TAM;f++)
            for(int c=0;c<TAM;c++)
                if(!celdas[f][c].tieneMina())
                    ((CeldaNumero)celdas[f][c])
                        .setMinasAlrededor(contarMinasAlrededor(f,c));
    }
}
