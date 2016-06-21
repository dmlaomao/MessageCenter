package sendprocessfactory;

import message.Message;
import sendprocess.MailSendProcess;
import sendprocess.SendProcess;

/**
 * Created by guozheng on 16/6/9.
 */
public class MailSendProcessFactory extends SendProcessFactory {

    /**
     * return a MailSendProcess
     * @param message
     * @return MailSendProcess
     */
    @Override
    public SendProcess createSendProcess(Message message) {
        return new MailSendProcess(message);
    }
}
