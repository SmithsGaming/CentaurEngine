package smithsgaming.centaurengine.util.exception;

/**
 * Created by Tim on 20/08/2016.
 */
public class SavableTagException extends RuntimeException {

    public SavableTagException() {
        super();
    }

    public SavableTagException(String s) {
        super(s);
    }

    public SavableTagException(String message, Throwable cause) {
        super(message, cause);
    }

    public SavableTagException(Throwable cause) {
        super(cause);
    }

}
