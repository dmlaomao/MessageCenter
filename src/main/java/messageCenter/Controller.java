package messageCenter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.NoMappingServerException;
import message.Message;
import server.HiServer;
import server.MailServer;
import server.Server;
import server.SmsServer;

/**
 * Created by guozheng on 16/6/6.
 */
public class Controller extends Thread {
    private static Logger logger = LogManager.getLogger(Controller.class);

    private static volatile Controller instance = null;
    private static BlockingQueue<Message> messageBlockingQueue = new LinkedBlockingQueue();
    private static Map<String, Server> stringTypeServerMap = new HashMap();

    private static int threadNumber = 2;
    private static int maxMsgPerMin = 60;

    private Controller () {
        initialMap();
    }

    /**
     * singleton
     * @return
     */
    public static Controller getInstance() {
        if (instance == null) {
            synchronized(Controller.class) {
                if (instance == null) {
                    instance = new Controller();
                }
            }
        }
        return instance;
    }

    public static void initialMap() {
        stringTypeServerMap.put("HiMessage", HiServer.getInstance(threadNumber, maxMsgPerMin));
        stringTypeServerMap.put("MailMessage", MailServer.getInstance(threadNumber, maxMsgPerMin));
        stringTypeServerMap.put("ShortMessage", SmsServer.getInstance(threadNumber,maxMsgPerMin));
    }

    /**
     * add new type of message and the according server to Map
     * @param messageType
     * @param server
     */
    public static void addTpyeServerMap(String messageType, Server server) {
        stringTypeServerMap.put(messageType, server);
        logger.info("add new Server: " + server.getClass().getName());
    }

    /**
     * put message into the messageBlockingQueue
     * @param message
     */
    public static void push(Message message) {
        messageBlockingQueue.offer(message);
        logger.info("add new message to MessageCenter: " + message.toString());
    }

    /**
     * dispatch message to its according server
     * @param message
     * @throws NoMappingServerException
     */
    public void dispatchMessage(Message message) throws NoMappingServerException {
        String type = message.getType();
        Server server = stringTypeServerMap.get(type);
        if (server == null) {
            throw new NoMappingServerException();
        } else {
            server.push(message);
            logger.info("dispatch message to " + server.getClass().getName() + ": " + message.toString());
        }
    }

    @Override
    public void run() {
        while (true) {
            Message message = messageBlockingQueue.poll();
            if (message == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    logger.error("error", e);
                }
            } else {
                try {
                    dispatchMessage(message);
                } catch (NoMappingServerException e) {
                    logger.error("no mapping server for message: " + message.toString());
                }
            }
        }
    }
}

