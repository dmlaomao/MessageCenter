package demo;

import message.HiMessage;
import message.MailMessage;
import message.Message;
import message.ShortMessage;
import messageCenter.Controller;
import server.HiServer;
import server.MailServer;
import server.SmsServer;

/**
 * For test message center
 * Created by guozheng on 16/6/11.
 */
public class ClientDemo {

    // Simulate creation and send message to message center
    public class CreateMessage extends Thread {
        @Override
        public void run() {
            int messageNumber = 0;
            while (true) {
                double random = Math.random();
                if (random < 0.3) {
                    Message message = new HiMessage("Alice", "Bob", "Hello", ++messageNumber);
                    Controller.push(message);
                } else if (random < 0.6) {
                    Message message = new MailMessage("Ponny", "Jack", "Hi", ++messageNumber);
                    Controller.push(message);
                } else if (random < 0.9) {
                    Message message = new ShortMessage("LiLei", "HanMeimei", "How are you", ++messageNumber);
                    Controller.push(message);
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Controller controller = Controller.getInstance();
        ClientDemo clientDemo = new ClientDemo();
        CreateMessage createMessage = clientDemo.new CreateMessage();
        createMessage.start();
        controller.start();
        HiServer.getInstance(2, 60).start();
        MailServer.getInstance(2, 60).start();
        SmsServer.getInstance(2, 60).start();
    }
}
