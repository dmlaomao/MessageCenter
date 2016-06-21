package sendprocessfactory;

import message.Message;
import sendprocess.HiSendProcess;
import sendprocess.SendProcess;

/**
 * Created by guozheng on 16/6/9.
 */
public class HiSendProcessFactory extends SendProcessFactory {

    /**
     * return a HiSendProcess
     * @param message
     * @return HiSendProcess
     */
    @Override
    public SendProcess createSendProcess(Message message) {
        return new HiSendProcess(message);
    }
}
