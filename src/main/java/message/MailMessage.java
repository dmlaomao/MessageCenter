package message;

/**
 * Created by guozheng on 16/6/6.
 */
public class MailMessage extends Message {
    public MailMessage(String sender, String receiver, String content, long id) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.type = "MailMessage";
        this.id = id;
    }
}
