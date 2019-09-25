
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;

@Named("dataBase")
@ApplicationScoped


public class DataBaseBean implements Serializable {

    private Map<String, UserBean> registeredUsers = new HashMap<String, UserBean>();
    private ArrayList<String> onlineUsers= new ArrayList<String>();
    private ArrayList<Message> messagesToShow = new ArrayList<Message>();

    public DataBaseBean(){
        System.out.println("CONSRUCTOOOOOR");
        UserBean User_prueba=new UserBean("AaRON55","123", "Aaron", "Salazar");
        UserBean User_prueba2= new UserBean("Sebas246", "123", "Sebastian", "Gutierrez");

        registeredUsers.put(User_prueba.getUserName(),User_prueba);
        registeredUsers.put(User_prueba2.getUserName(),User_prueba2);
    }

    public Map<String, UserBean> getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(Map<String, UserBean> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public ArrayList<String> getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(ArrayList<String> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    public ArrayList<Message> getMessagesToShow() {
        return messagesToShow;
    }

    public void setMessagesToShow(ArrayList<Message> messagesToShow) {
        this.messagesToShow = messagesToShow;
    }

    public String registerUser(UserBean newUser){
        System.out.println(newUser.getUserName());

       UserBean existingUser= getRegisteredUsers().get(newUser.getUserName());

        if(existingUser!=null){
            System.out.println("EXISTING");
            return "failed";
        }

        else{
            System.out.println("NO HAY PUEDE REgistrarse");
            registeredUsers.put(newUser.getUserName(),newUser);
            System.out.println("NO HAY PUEDE REgistrarse");
        }
        return "success";
    }
}
