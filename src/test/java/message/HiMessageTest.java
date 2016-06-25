package message;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by guozheng on 16/6/11.
 */
public class HiMessageTest {
    @Test
    public void getTypeTest() throws Exception {
        Assert.assertEquals("HiMessage", new HiMessage("Alice", "Bob", "hello", 1).getType());
    }

    @Test
    public void toStringTest() throws Exception {
        String expectResult = "Message type = HiMessage Message id = 1 Message sender = Alice Message receiver = Bob";
        Assert.assertEquals(expectResult, new HiMessage("Alice", "Bob", "hello", 1).toString());
    }

}