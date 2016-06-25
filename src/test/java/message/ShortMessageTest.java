package message;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by guozheng on 16/6/11.
 */
public class ShortMessageTest {
    @Test
    public void getTypeTest() throws Exception {
        Assert.assertEquals("ShortMessage", new ShortMessage("Alice", "Bob", "hello", 1).getType());
    }

    @Test
    public void toStringTest() throws Exception {
        String expectResult = "Message type = ShortMessage Message id = 1 Message sender = Alice Message receiver = "
                + "Bob";
        Assert.assertEquals(expectResult, new ShortMessage("Alice", "Bob", "hello", 1).toString());
    }
}