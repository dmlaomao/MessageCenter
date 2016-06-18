package sendProcess;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import message.Message;

/**
 * Created by guozheng on 16/6/8.
 */
public class MailSendProcess extends SendProcess {
    private Logger logger = LogManager.getLogger(MailSendProcess.class);

    public MailSendProcess(Message message) {
        super(message);
    }

    @Override
    protected void send() {
        //MailServer send process
        logger.info("sending MailMessage: " + message.toString());
    }
}
