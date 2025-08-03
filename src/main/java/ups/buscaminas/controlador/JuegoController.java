package ups.buscaminas.controlador;

import ups.buscaminas.modelo.*;
import ups.buscaminas.modelo.excepciones.*;
import ups.buscaminas.persistencia.GameIO;
import ups.buscaminas.vista.*;

public class JuegoController {
    private Juego juego = new Juego();
    private final Vista vista = new ConsolaVista();

    public void iniciar() {
        vista.mostrarMensaje("=== BUSCAMINAS ===");
        while (!juego.isTerminado()) {
            vista.mostrar(juego);
            procesarComando(vista.leerComando());
        }
        vista.mostrar(juego);
        vista.mostrarMensaje(juego.isVictoria() ? "¡Ganaste!" : "Boom – perdiste.");
    }

    private void procesarComando(String cmd) {
        try {
            if (cmd.equalsIgnoreCase("Q")) System.exit(0);

            if (cmd.equalsIgnoreCase("G")) { GameIO.guardar(juego); vista.mostrarMensaje("Partida guardada."); return; }
            if (cmd.equalsIgnoreCase("C")) {
                if (GameIO.existePartida()) { juego = GameIO.cargar(); vista.mostrarMensaje("Partida cargada."); }
                else vista.mostrarMensaje("No hay partida guardada.");
                return;
            }

            String[] p = cmd.split("\\s+");
            if (p.length!=2) { vista.mostrarError("Sintaxis inválida."); return; }
            Coordenada c = Coordenada.parse(p[1]);
            switch (p[0].toUpperCase()) {
                case "D" -> juego.descubrir(c);
                case "M" -> juego.marcar(c);
                default  -> vista.mostrarError("Comando desconocido.");
            }
        } catch (IllegalArgumentException | CoordenadaInvalidaException | CeldaYaDescubiertaException e) {
            vista.mostrarError(e.getMessage());
        } catch (java.io.IOException | ClassNotFoundException e) {
            vista.mostrarError("Error de E/S: " + e.getMessage());
        }
    }
}
