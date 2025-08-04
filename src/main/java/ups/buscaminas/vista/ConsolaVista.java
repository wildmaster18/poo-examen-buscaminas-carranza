package ups.buscaminas.vista;

import java.util.Scanner;
import ups.buscaminas.modelo.*;

// Implementación de la interfaz Vista para consola.
public class ConsolaVista implements Vista {

    private static final boolean USE_COLOR = true;
    private static final String RESET    = "\u001B[0m";
    private static final String FG_GREY  = "\u001B[90m";
    private static final String FG_GREEN = "\u001B[32m";
    private static final String FG_CYAN  = "\u001B[36m";
    private static final String FG_RED   = "\u001B[31m";

    // Línea horizontal para separar filas del tablero.
    private static final String SEP_H = "   +" + "---+".repeat(Tablero.TAM);
    private final Scanner sc = new Scanner(System.in);

	// Tabla de símbolos:
    @Override
    public void mostrar(Juego juego) {
        imprimirCabeceraColumnas();
        System.out.println(SEP_H);

        // Imprimir filas del tablero
        Tablero t = juego.getTablero();
        for (int f = 0; f < Tablero.TAM; f++) {
            System.out.printf(" %c |", 'A' + f);
            for (int c = 0; c < Tablero.TAM; c++)
                System.out.printf(" %s |", colorizar(t.get(f, c).simbolo()));
            System.out.println();
            System.out.println(SEP_H);
        }

        // Mostrar estado del juego
        if (!juego.isTerminado())
            System.out.printf("%nMinas restantes: %d%n", juego.getMinasRestantes());
    }

    // Imprime la cabecera con los números de las columnas.
    private void imprimirCabeceraColumnas() {
        System.out.print("    ");
        for (int c = 1; c <= Tablero.TAM; c++)
            System.out.print(" " + c + (c < 10 ? "  " : " "));
        System.out.println();
    }

    // Devuelve el carácter coloreado según su tipo.
    private String colorizar(char s) {
        if (!USE_COLOR) return String.valueOf(s);
        return switch (s) {
            case '#' -> FG_GREY  + s + RESET;
            case 'V' -> FG_GREEN + s + RESET;
            case 'X' -> FG_RED   + s + RESET;
            default  -> Character.isDigit(s) ? FG_CYAN + s + RESET : String.valueOf(s);
        };
    }

    // Lee un comando del usuario.
    @Override
    public String leerComando() {
        System.out.println();
        System.out.println("+-----------------------+------------------------------+");
        System.out.println("|         Entrada       |            Acción            |");
        System.out.println("+-----------------------+------------------------------+");
        System.out.println("| A5 (FilaColumna)      | Descubrir celda              |");
        System.out.println("| M B7      	        | Marcar o desmarcar celda     |");
        System.out.println("| G         			| Guardar partida              |");
        System.out.println("| C        				| Cargar partida               |");
        System.out.println("| Q         			| Salir del juego              |");
        System.out.println("+-----------------------+------------------------------+");
        System.out.print("Ingrese acción: ");
        return sc.nextLine().trim();
    }

    // Mostrar mensajes y errores.
    @Override public void mostrarMensaje(String m) { System.out.println(m); }
    @Override public void mostrarError  (String e) { System.err.println(e); }
}

