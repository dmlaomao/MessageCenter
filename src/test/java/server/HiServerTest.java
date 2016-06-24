package server;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by guozheng on 16/6/11.
 */
public class HiServerTest {
    private HiServer hiServer = HiServer.getInstance(2, 60);

    @Test
    public void getInstance() throws Exception {
        Assert.assertSame(hiServer, HiServer.getInstance(2, 60));
    }
}