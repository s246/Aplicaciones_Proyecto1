
import java.io.Serializable;

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

    public String getGreeting() {
        if (userName.length() == 0) return "";
        else return "Welcome to JSF2 + Ajax, " + userName + "!";
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