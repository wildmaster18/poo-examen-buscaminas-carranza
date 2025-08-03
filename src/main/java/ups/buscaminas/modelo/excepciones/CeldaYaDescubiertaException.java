package ups.buscaminas.modelo.excepciones;
public class CeldaYaDescubiertaException extends Exception {
    public CeldaYaDescubiertaException() { super("La celda ya está descubierta."); }
}
