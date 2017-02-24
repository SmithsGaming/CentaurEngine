package smithsgaming.centaurengine.util.exception;

/**
 * Created by Tim on 07/09/2016.
 */
public class SpriteRegistrationException extends RuntimeException {

    public SpriteRegistrationException() {
        super();
    }

    public SpriteRegistrationException(String s) {
        super(s);
    }

    public SpriteRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpriteRegistrationException(Throwable cause) {
        super(cause);
    }
}
