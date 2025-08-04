package ups.buscaminas.modelo.excepciones;

// Excepción lanzada cuando se intenta descubrir una celda que ya ha sido descubierta.
public class CeldaYaDescubiertaException extends Exception {
    // Constructor que inicializa el mensaje de la excepción.
	public CeldaYaDescubiertaException() { super("La celda ya está descubierta."); }
}
