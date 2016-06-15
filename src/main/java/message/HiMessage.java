package message;

/**
 * Created by guozheng on 16/6/6.
 */
public class HiMessage extends Message {
    public HiMessage(String sender, String receiver, String content, long id) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.type = "HiMessage";
        this.id = id;
    }
}
