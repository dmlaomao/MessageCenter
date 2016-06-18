package sendProcess;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import message.Message;

/**
 * Created by guozheng on 16/6/9.
 */
public class SmsSendProcess extends SendProcess {
    private Logger logger = LogManager.getLogger(SmsSendProcess.class);

    public SmsSendProcess(Message message) {
        super(message);
    }

    @Override
    protected void send() {
        //SmsServer send process
        logger.info("sending SmsMessage: " + message.toString());
    }
}
