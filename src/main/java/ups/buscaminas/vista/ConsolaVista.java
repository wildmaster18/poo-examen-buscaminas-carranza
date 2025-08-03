package ups.buscaminas.vista;

import java.util.Scanner;
import ups.buscaminas.modelo.*;

public class ConsolaVista implements Vista {
    private final Scanner sc = new Scanner(System.in);

    @Override public void mostrar(Juego juego) {
        Tablero t = juego.getTablero();
        System.out.print("   ");
        for (int i=1;i<=Tablero.TAM;i++) System.out.printf("%2d ", i);
        System.out.println();
        for (int f=0; f<Tablero.TAM; f++) {
            System.out.printf("%c |", 'A'+f);
            for (int c=0; c<Tablero.TAM; c++)
                System.out.printf(" %c ", t.get(f,c).simbolo());
            System.out.println();
        }
    }

    @Override
    public String leerComando() {
        System.out.println("\nComandos: "
            + "D <Fila/Columna> = Descubrir | "
            + "M <Fila/Columna> = Marcar | "
            + "G = Guardar | "
            + "C = Cargar | "
            + "Q = Salir");
        System.out.print("Ejemplos: D A5   M B7\n> ");
        return sc.nextLine().trim();
    }

    @Override public void mostrarMensaje(String msg) { System.out.println(msg); }
    @Override public void mostrarError(String err)   { System.err.println(err); }
}
