package exception;

/**
 * Created by guozheng on 16/6/8.
 */
public class SendFailException extends RuntimeException {

    public SendFailException() {
        super();
    }

    public SendFailException(String msg) {
        super(msg);
    }
}
