package sendprocessfactory;

import message.Message;
import sendprocess.SendProcess;

/**
 * return a runnable sendprocess object
 * Created by guozheng on 16/6/8.
 */
public abstract class SendProcessFactory {
    public abstract SendProcess createSendProcess(Message message);
}
