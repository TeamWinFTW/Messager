package ie.nodstuff.messager;

import java.io.Serializable;

/**
 * Created by nodstuff on 15/01/15.
 */
public class Message implements Serializable{

    private String sender;
    private String message;

    @Override
    public String toString() {
        return
                sender+"\n"+message;
    }

    public Message(String sender, String message){
        this.sender=sender;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {

        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
