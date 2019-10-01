
import java.io.Serializable;
import java.util.ArrayList;

import javax.inject.Named;
// or import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
// or import javax.faces.bean.SessionScoped;

@Named("user") // or @ManagedBean(name="user")
@SessionScoped

public class UserBean implements Serializable {
    private String userName = "";
    private String password;
    private String name = "";
    private String lastName = "";
    private Message message=new Message();
    private String idToEliminate="0";
    private ArrayList<Message> messagesToShow = new ArrayList<Message>();

    public UserBean(){

    }

    public UserBean(String userName, String password, String name, String lastName) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }

    public String getIdToEliminate() {
        return idToEliminate;
    }

    public void setIdToEliminate(String idToEliminate) {
        this.idToEliminate=idToEliminate;
    }

    public ArrayList<Message> getMessagesToShow() {
        return messagesToShow;
    }

    public void setMessagesToShow(ArrayList<Message> messagesToShow) {
        this.messagesToShow = messagesToShow;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String newValue) {
        userName = newValue;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newValue) {
        password = newValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String newValue) {
        name = newValue;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}