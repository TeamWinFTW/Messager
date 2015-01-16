package ie.nodstuff.messager;

import java.io.Serializable;

/**
 * Created by nodstuff on 15/01/15.
 */
public class Contact implements Serializable{

    private String name;
    private String id;

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
    }


}