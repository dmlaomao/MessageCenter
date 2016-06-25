package server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import message.Message;
import sendprocess.SendProcess;
import sendprocessfactory.SendProcessFactory;

/**
 * Multiple sendprocess thread to send for efficiency
 * Thread pool is used
 * Send messages got from Controller with speed limit
 * Created by guozheng on 16/6/6.
 */
public abstract class Server extends Thread {
    public BlockingQueue<Message> messageBlockingQueue;

    protected Logger logger = LogManager.getLogger(Server.class);
    protected SendProcessFactory sendProcessFactory;
    protected ExecutorService pool;
    // number of max messages allowed to send per minute
    protected int maxMsgPerMinute;
    protected AtomicInteger countMsg = new AtomicInteger(0);
    // for limit speed
    protected long timeStamp;

    /**
     * push message to messageBlockingQueue
     * @param message
     */
    public void push(Message message) {
        messageBlockingQueue.offer(message);
    }

    /**
     * simulate the send message process with speed limit
     */
    protected void sendMessageLimitSpeed() {
        timeStamp = System.currentTimeMillis() + 60000;
        while (true) {
            Message message = messageBlockingQueue.poll();
            if (message == null) {
                try {
                    Thread.sleep(1000);
                    logger.info(this.getClass().getName() + ": no message to send and sleep 1 second");
                } catch (InterruptedException e) {
                    logger.error("error", e);
                }
            } else {
                long currentTime = System.currentTimeMillis();
                if (currentTime < timeStamp && countMsg.get() < maxMsgPerMinute) {
                    SendProcess sendProcess = sendProcessFactory.createSendProcess(message);
                    pool.execute(sendProcess);
                    countMsg.getAndIncrement();
                    logger.info(this.getClass().getName() + ": successfully send a message:" + message.toString());
                } else {
                    try {
                        logger.info(this.getClass().getName() + ": reach speed limit and sleep");
                        Thread.sleep(timeStamp - currentTime);
                    } catch (InterruptedException e) {
                        logger.error("error", e);
                    } finally {
                        logger.info(this.getClass().getName() + ": wake and start to send");
                        timeStamp = System.currentTimeMillis() + 60000;
                        countMsg.set(0);
                    }

                }
            }
        }
    }
}
