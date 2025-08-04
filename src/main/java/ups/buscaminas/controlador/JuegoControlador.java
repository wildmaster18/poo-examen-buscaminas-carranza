
package ups.buscaminas.controlador;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import ups.buscaminas.modelo.*;
import ups.buscaminas.modelo.excepciones.*;
import ups.buscaminas.persistencia.GameIO;
import ups.buscaminas.vista.*;

// Controlador principal del juego de Buscaminas
public class JuegoControlador {

	// BANNER DE BIENVENIDA
    private static final String BANNER =
        "+=============================================+\n" +
        "|              B U S C A M I N A S            |\n" +
        "+=============================================+\n";

    // Mensaje de ayuda para comandos inválidos
    private static final String AYUDA_ERROR =
        "Entrada no válida.\n" +
        "Comandos permitidos:\n" +
        "  FilaColumna    Descubrir  (ej. A5)\n" +
        "  M FilaColumna  Marcar     (ej. M B7)\n" +
        "  G              Guardar\n" +
        "  C              Cargar\n" +
        "  Q              Salir";

    // Estado del juego
    private Juego juego;
    private final Vista vista = new ConsolaVista();

	// Inicia el juego y maneja el bucle principal
    public void iniciar() {
        System.out.println(BANNER);
        Scanner sc = new Scanner(System.in);

        // Bucle principal del juego
        boolean repetir = true;
        while (repetir) {
           
        	// Nueva partida
        	juego = new Juego();
            while (!juego.isTerminado()) {
                vista.mostrar(juego);
                procesarComando(vista.leerComando());
            }
            
            // Mostrar el estado final del juego
            vista.mostrar(juego);
            vista.mostrarMensaje(
                juego.isVictoria() ? "¡Ganaste!" : "La bomba ha explotado!! Intentalo de nuevo.");

            // Preguntar si se desea iniciar una nueva partida
            String r;
            do {
                System.out.print("¿Iniciar nueva partida? (Si: S / No: N): ");
                r = sc.nextLine().trim().toUpperCase();
            } while (!r.equals("S") && !r.equals("N"));

            repetir = r.equals("S");
        }

        // Despedida
        vista.mostrarMensaje("Gracias por jugar.");
    }

    // Procesa un comando ingresado por el usuario.
    private void procesarComando(String cmd) {
        try {
            String[] p = cmd.trim().split("\\s+");

            // Comando vacío
            if (p.length == 1) {
                String t = p[0].toUpperCase();

                switch (t) {
                
                	// comando salir
                    case "Q":
                        System.exit(0);
                        return;
                        
                    // comando guardar
                    case "G":
                        GameIO.guardar(juego);
                        vista.mostrarMensaje("Partida guardada en: "
                                + GameIO.getRutaGuardado());
                        return;
                    
                    // comando cargar
                    case "C":
                        if (GameIO.existePartida()) {
                            juego = GameIO.cargar();
                            vista.mostrarMensaje("Partida cargada.");
                        } 
                        
                        // no existe partida guardada
                        else {
                            vista.mostrarMensaje("No hay partida guardada.");
                        }
                        return;
                    
                    // comando descubrir
                    default:
                        if (t.matches("[A-J](10|[1-9])")) {
                            juego.descubrir(Coordenada.parse(t));
                        } else {
                            vista.mostrarError(AYUDA_ERROR);
                        }
                        return;
                }
            }

            // Comando marcar
            if (p.length == 2) {
                if ("M".equalsIgnoreCase(p[0])) {
                    juego.marcar(Coordenada.parse(p[1]));
                } 
                // Formato inválido para marcar
                else {
                    vista.mostrarError(AYUDA_ERROR);
                }
                return;
            }

            // Comando no reconocido
            vista.mostrarError(AYUDA_ERROR);

            // Captura de excepciones
        } catch (IllegalArgumentException | InputMismatchException |
                 CoordenadaInvalidaException | CeldaYaDescubiertaException e) {

            // Mostrar mensaje de error para entradas inválidas
            vista.mostrarError(AYUDA_ERROR);

            // Captura de excepción para fin de entrada
        } catch (NoSuchElementException e) {
            vista.mostrarError("Entrada finalizada por el usuario.");
            System.exit(0);

            // Captura de excepción para coordenadas fuera del tablero
        } catch (ArrayIndexOutOfBoundsException e) {
            vista.mostrarError("Coordenada fuera del tablero (A-J, 1-10).");

            // Captura de excepciones de E/S y de clase no encontrada
        } catch (java.io.IOException | ClassNotFoundException e) {
            vista.mostrarError("Error de E/S: " + e.getMessage());
        }
    }
}
