package message;

/**
 * Created by guozheng on 16/6/6.
 */
public class ShortMessage extends Message {
    public ShortMessage(String sender, String receiver, String content, long id) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.type = "ShortMessage";
        this.id = id;
    }
}
