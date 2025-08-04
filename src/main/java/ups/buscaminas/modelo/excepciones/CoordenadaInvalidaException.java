package ups.buscaminas.modelo.excepciones;
// Excepción lanzada cuando se intenta acceder a una coordenada fuera del rango del tablero.
public class CoordenadaInvalidaException extends Exception {
    // Constructor que inicializa el mensaje de la excepción.
	public CoordenadaInvalidaException() { super("Coordenada fuera de rango."); }
}
