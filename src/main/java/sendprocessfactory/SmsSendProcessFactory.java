package sendprocessfactory;

import message.Message;
import sendprocess.SendProcess;
import sendprocess.SmsSendProcess;

/**
 * Created by guozheng on 16/6/10.
 */
public class SmsSendProcessFactory extends SendProcessFactory {

    /**
     * return a SmsSendProcess
     * @param message
     * @return SmsSendProcess
     */
    @Override
    public SendProcess createSendProcess(Message message) {
        return new SmsSendProcess(message);
    }
}
