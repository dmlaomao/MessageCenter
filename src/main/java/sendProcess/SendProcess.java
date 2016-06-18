package sendProcess;

import exception.SendFailException;
import message.Message;

/**
 * Simulate the sending process
 * Created by guozheng on 16/6/8.
 */
public abstract class SendProcess implements Runnable {
    protected Message message;

    public SendProcess(Message message) {
        this.message = message;
    }

    protected void send() throws SendFailException {

    }

    @Override
    public void run() {
        try {
            send();
        } catch (SendFailException e) {
            e.printStackTrace();
        }
    }
}
