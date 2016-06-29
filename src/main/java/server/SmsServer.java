package server;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import sendprocessfactory.SmsSendProcessFactory;

/**
 * Created by guozheng on 16/6/6.
 */
public class SmsServer extends Server {
    private static volatile SmsServer instance = null;

    private SmsServer(int threadNumber, int maxMsgPerMin) {
        messageBlockingQueue = new LinkedBlockingQueue();
        sendProcessFactory = new SmsSendProcessFactory();
        pool = Executors.newFixedThreadPool(threadNumber);
        this.maxMsgPerMinute = maxMsgPerMin;
    }

    /**
     * return a singleton of SmsServer
     * @param threadNumber
     * @param maxMsgPerMin
     * @return SmsServer instance
     */
    public static SmsServer getInstance(int threadNumber, int maxMsgPerMin) {
        if (instance == null) {
            synchronized (SmsServer.class) {
                if (instance == null) {
                    instance = new SmsServer(threadNumber, maxMsgPerMin);
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
