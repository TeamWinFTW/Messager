package ie.nodstuff.messager;

import java.util.ArrayList;

/**
 * Created by nodstuff on 01/12/14.
 */
public class Controller {
    private ArrayList<Message> msgs = new ArrayList<Message>();
    private ArrayList<Contact> contacts = new ArrayList<Contact>();
    private static Controller c=null;
    private String id;

    private Controller(){

    }

    public static Controller getInstance(){
        if(c==null){
            c = new Controller();
        }
        return c;
    }

    public void addToMessages(Message m){
        msgs.add(m);
    }

    public void addToContacts(String name, String id){
        Contact c = new Contact(name,id);
        contacts.add(c);
    }

    public ArrayList<Message> getMessages(){
        return msgs;
    }

    public Contact returnContact(int position){
        return contacts.get(position);
    }

    public ArrayList<Contact> getContacts(){
        return contacts;
    }

    public Message returnMessage(int position){
        return msgs.get(position);
    }

    public int getSizeMessages(){
        return msgs.size();
    }

}
