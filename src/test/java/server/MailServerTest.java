package server;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by guozheng on 16/6/12.
 */
public class MailServerTest {
    private MailServer mailServer = MailServer.getInstance(2, 60);

    @Test
    public void getInstance() throws Exception {
        Assert.assertSame(mailServer, MailServer.getInstance(2, 60));
    }

}