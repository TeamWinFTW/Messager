package ie.nodstuff.messager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nodstuff on 15/01/15.
 */
public class Contact implements Serializable{

    private String name;
    private String id;
    private List<Message> messageList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;

    }

    public Contact(String name, String id){
        this.name=name;
        this.id=id;
        messageList=new ArrayList<>();
    }

    public void addMessage(Message m){
        messageList.add(m);
    }


    public List<Message> getMessageList(){
        return messageList;
    }
}
