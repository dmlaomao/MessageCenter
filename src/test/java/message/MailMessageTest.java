package message;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by guozheng on 16/6/11.
 */
public class MailMessageTest {
    @Test
    public void getTypeTest() throws Exception {
        Assert.assertEquals("MailMessage", new MailMessage("Alice", "Bob", "hello", 1).getType());
    }

    @Test
    public void toStringTest() throws Exception {
        String expectResult = "Message type = MailMessage Message id = 1 Message sender = Alice Message receiver = Bob";
        Assert.assertEquals(expectResult, new MailMessage("Alice", "Bob", "hello", 1).toString());
    }

}