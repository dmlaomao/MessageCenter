package message;

/**
 * Created by guozheng on 16/6/6.
 */
public abstract class Message {
    protected String sender;
    protected String receiver;
    protected String content;
    protected String type;
    protected long id;

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Message type = " + this.type + " Message id = " + this.id + " Message sender = "
                + this.sender + " Message receiver = " + this.receiver;
    }
}
