package sendProcess;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import message.Message;

/**
 * Created by guozheng on 16/6/8.
 */
public class HiSendProcess extends SendProcess {
    private Logger logger = LogManager.getLogger(HiSendProcess.class);

    public HiSendProcess(Message message) {
        super(message);
    }

    @Override
    protected void send() {
        //HiServer send process
        logger.info("sending HiMessage: " + message.toString());
    }
}
