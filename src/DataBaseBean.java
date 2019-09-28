
import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;

@Named("dataBase")
@ApplicationScoped


public class DataBaseBean implements Serializable {

    private Map<String, UserBean> registeredUsers = new HashMap<String, UserBean>();
    private ArrayList<String> onlineUsers = new ArrayList<String>();
    private ArrayList<String> offlineUsers = new ArrayList<String>();
    private ArrayList<Message> messagesToShow = new ArrayList<Message>();
    private ArrayList<Message> total_messages = new ArrayList<Message>();

    public DataBaseBean() {
        System.out.println("CONSRUCTOOOOOR");
        UserBean User_prueba = new UserBean("AaRON55", "123", "Aaron", "Salazar");
        UserBean User_prueba2 = new UserBean("Sebas246", "123", "Sebastian", "Gutierrez");

        registeredUsers.put(User_prueba.getUserName(), User_prueba);
        registeredUsers.put(User_prueba2.getUserName(), User_prueba2);
        total_messages.add(new Message("HOLAAA",User_prueba2, User_prueba,"hoy"));
    }

    public Map<String, UserBean> getRegisteredUsers() {
        return registeredUsers;

    }


    public ArrayList<Message> getTotal_messages() {
        return total_messages;
    }

    public void setTotal_messages(ArrayList<Message> total_messages) {
        this.total_messages = total_messages;
    }

    public ArrayList<String> getOfflineUsers() {
        offlineUsers.clear();
        for (String key: registeredUsers.keySet()){
            if(!onlineUsers.contains(key)){
                offlineUsers.add(key);
            }
        }
        System.out.println(offlineUsers);
        return offlineUsers;
    }

    public void setOfflineUsers(ArrayList<String> offlineUsers) {
        this.offlineUsers = offlineUsers;
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


    public void obtainMessages(){
        messagesToShow.clear();

        FacesContext facesContext=FacesContext.getCurrentInstance();
        Map<String,String> parametros=facesContext.getExternalContext().getRequestParameterMap();

        String userName=parametros.get("userName");
        System.out.println(userName);

        for(Message message: total_messages){
            if(message.getDestinatario().equals(userName)){
                messagesToShow.add(message);
                System.out.println(message.getContent());
            }
        }
        System.out.println(messagesToShow);

    }
    public String registerUser() {

        FacesContext facesContext=FacesContext.getCurrentInstance();
        Map<String,String> parametros=facesContext.getExternalContext().getRequestParameterMap();

        String userName=parametros.get("userName");
        String userPass=parametros.get("password");
        String name=parametros.get("name");
        String lastName=parametros.get("lastName");

        UserBean existingUser=registeredUsers.get(userName);


        if (existingUser != null) {
            System.out.println("EXISTING");
            return "failure";
        }
        else {
            System.out.println("NO HAY entonces si PUEDE REgistrarse");
            registeredUsers.put(userName, new UserBean(userName, userPass, name, lastName));
        }
        return "success";
    }

    public String validateUser(){
        FacesContext facesContext=FacesContext.getCurrentInstance();
        Map<String,String> parametros=facesContext.getExternalContext().getRequestParameterMap();

        String userName=parametros.get("userName");
        String userPass=parametros.get("password");

        System.out.println("IMPRIMIO");
        System.out.println(userName);

        UserBean user=registeredUsers.get(userName);

        if (user==null){
            return "failure";
        }

        else{
            String rightPass=user.getPassword();
            if(rightPass.equals(userPass)){
                onlineUsers.add(userName);
                return "success";
            }
            else
                return "failure";
        }

    }

}
