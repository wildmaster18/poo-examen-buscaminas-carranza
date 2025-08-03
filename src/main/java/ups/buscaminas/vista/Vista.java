package ups.buscaminas.vista;
import ups.buscaminas.modelo.Juego;

public interface Vista {
    void mostrar(Juego juego);
    String leerComando();
    void mostrarMensaje(String msg);
    void mostrarError(String err);
}
