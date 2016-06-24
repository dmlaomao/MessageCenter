package server;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by guozheng on 16/6/12.
 */
public class SmsServerTest {
    private SmsServer smsServer = SmsServer.getInstance(2, 60);

    @Test
    public void getInstance() throws Exception {
        Assert.assertSame(smsServer, SmsServer.getInstance(2, 60));
    }

}