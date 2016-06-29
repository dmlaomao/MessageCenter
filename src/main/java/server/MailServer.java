package server;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import sendprocessfactory.MailSendProcessFactory;

/**
 * Created by guozheng on 16/6/6.
 */
public class MailServer extends Server {
    private static volatile MailServer instance = null;

    private MailServer(int threadNumber, int maxMsgPerMin) {
        messageBlockingQueue = new LinkedBlockingQueue();
        sendProcessFactory = new MailSendProcessFactory();
        pool = Executors.newFixedThreadPool(threadNumber);
        this.maxMsgPerMinute = maxMsgPerMin;
    }

    /**
     * return a singleton of MailServer
     * @param threadNumber
     * @param maxMsgPerMin
     * @return MailServer instance
     */
    public static MailServer getInstance(int threadNumber, int maxMsgPerMin) {
        if (instance == null) {
            synchronized (MailServer.class) {
                if (instance == null) {
                    instance = new MailServer(threadNumber, maxMsgPerMin);
                }
            }
        }
        return instance;
    }

    @Override
    public void run() {
        this.sendMessageLimitSpeed();
    }
}
