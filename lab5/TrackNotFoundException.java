/**
 * Викидається, якщо жодна композиція не потрапила
 * у вказаний користувачем діапазон тривалості.
 */
public class TrackNotFoundException extends Exception {
    public TrackNotFoundException(String message) {
        super(message);
    }
}
