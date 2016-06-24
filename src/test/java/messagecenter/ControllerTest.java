package messagecenter;

import org.junit.Assert;
import org.junit.Test;

import message.HiMessage;
import message.MailMessage;
import message.Message;
import message.ShortMessage;
import server.HiServer;
import server.MailServer;
import server.SmsServer;

/**
 * Created by guozheng on 16/6/12.
 */
public class ControllerTest {
    private Controller controller = Controller.getInstance();

    @Test
    public void getInstance() throws Exception {
        Assert.assertSame(controller, Controller.getInstance());
    }

    @Test
    public void initialMap() throws Exception {
        Assert.assertSame(HiServer.getInstance(2, 60), Controller.stringTypeServerMap.get("HiMessage"));
        Assert.assertSame(MailServer.getInstance(2, 60), Controller.stringTypeServerMap.get("MailMessage"));
        Assert.assertSame(SmsServer.getInstance(2, 60), Controller.stringTypeServerMap.get("ShortMessage"));
    }

    @Test
    public void dispatchMessage() throws Exception {
        Message hiMessage = new HiMessage("Alice", "Bob", "hello", 1);
        Message mailMessage = new MailMessage("Alice", "Bob", "hello", 1);
        Message shortMessage = new ShortMessage("Alice", "Bob", "hello", 1);
        controller.dispatchMessage(hiMessage);
        controller.dispatchMessage(mailMessage);
        controller.dispatchMessage(shortMessage);
        Assert.assertTrue(Controller.stringTypeServerMap.get("HiMessage").messageBlockingQueue.contains(hiMessage));
        Assert.assertTrue(Controller.stringTypeServerMap.get("MailMessage").messageBlockingQueue.contains(mailMessage));
        Assert.assertTrue(Controller.stringTypeServerMap.get("ShortMessage").messageBlockingQueue.contains
                (shortMessage));
    }

}