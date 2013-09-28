package batch.exception;

/**
 * Exception lancée par le parseur.
 *
 * @author Karim BENHDECH
 */
public class ParsingException extends RuntimeException {

    /**
     * Constructeur.
     *
     * @param message cause de l'exception
     */
    public ParsingException(final String message) {
        super(message);
    }
}
