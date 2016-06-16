package exception;

/**
 * Thrown to indicate no mapping server is found for the given message type
 * Created by guozheng on 16/6/6.
 */
public class NoMappingServerException extends RuntimeException {
    public NoMappingServerException() {
        super();
    }

    public NoMappingServerException(String msg) {
        super(msg);
    }
}
