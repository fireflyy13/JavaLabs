/**
 * Виникає, коли тривалість композиції вказана як нульова або від’ємна.
 */
public class InvalidDurationException extends RuntimeException {
    public InvalidDurationException(String message) {
        super(message);
    }
}
