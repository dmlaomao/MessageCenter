package server;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import sendprocessfactory.HiSendProcessFactory;

/**
 * Created by guozheng on 16/6/6.
 */
public class HiServer extends Server {
    private static volatile HiServer instance = null;

    private HiServer(int threadNumber, int maxMsgPerMin) {
        messageBlockingQueue = new LinkedBlockingQueue();
        sendProcessFactory = new HiSendProcessFactory();
        pool = Executors.newFixedThreadPool(threadNumber);
        this.maxMsgPerMinute = maxMsgPerMin;
    }

    /**
     * return a singleton of HiServer
     * @param threadNumber
     * @param maxMsgPerMin
     * @return Hi
     */
    public static HiServer getInstance(int threadNumber, int maxMsgPerMin) {
        if (instance == null) {
            synchronized (HiServer.class) {
                if (instance == null) {
                    instance = new HiServer(threadNumber, maxMsgPerMin);
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
