
import java.io.Serializable;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;


@Named("dataBase")
@ApplicationScoped


public class DataBaseBean implements Serializable {

    private Map<String, UserBean> registeredUsers = new HashMap<String, UserBean>();
    private ArrayList<String> onlineUsers = new ArrayList<String>();
    private ArrayList<String> offlineUsers = new ArrayList<String>();
    private ArrayList<String> allUsers = new ArrayList<String>();
    private ArrayList<Message> messagesToShow = new ArrayList<Message>();
    private ArrayList<Message> total_messages = new ArrayList<Message>();
    private int idCounter=0;

    public DataBaseBean() {
        System.out.println("CONSRUCTOOOOOR DATABASE BEAN");
        UserBean User_prueba = new UserBean("Aaron", "123", "Aaron", "Salazar");
        UserBean User_prueba2 = new UserBean("Sebas246", "123", "Sebastian", "Gutierrez");

        registeredUsers.put(User_prueba.getUserName(), User_prueba);
        registeredUsers.put(User_prueba2.getUserName(), User_prueba2);
        total_messages.add(new Message(5000,"HOLAAA",User_prueba2.getUserName(), User_prueba.getUserName(),"hoy"));
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
        return offlineUsers;
    }

    public ArrayList<String> getAllUsers() {
        allUsers.clear();
        for (String key: registeredUsers.keySet()){
            allUsers.add(key);
        }
        // System.out.println(offlineUsers);
        return allUsers;
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


    public void obtainMessages(UserBean actualUser){

        messagesToShow.clear();

        String actualUsername= actualUser.getUserName();

        for(Message message: total_messages){
            if(message.getDestinatario().equals(actualUsername)){
                messagesToShow.add(message);
                System.out.println(message.getContent());
            }
        }

    }

    public void addMessageTosend(UserBean actualUser){
        FacesContext facesContext=FacesContext.getCurrentInstance();
        System.out.println("EL DESTINATARIO ES");
        System.out.println(actualUser.getMessage().getDestinatario());

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        if(actualUser.getMessage().getDestinatario()==null){
            facesContext.addMessage("EnviarButton", new FacesMessage("FALTA SELECCIONAR DESTINATARIO"));
        }

        else {
        total_messages.add(new Message(idCounter,actualUser.getMessage().getContent(),actualUser.getMessage().getDestinatario(),actualUser.getUserName(),timeStamp));
        idCounter++;
        System.out.println(total_messages.toString());
        facesContext.addMessage("EnviarButton", new FacesMessage("Enviado"));
        actualUser.getMessage().setContent("");
        }
    }


    public void eliminateMessage(UserBean actualUser){
        FacesContext facesContext=FacesContext.getCurrentInstance();
        System.out.println(actualUser.getIdToEliminate());
        if(actualUser.getIdToEliminate()==null){
            facesContext.addMessage("EliminarMessage", new FacesMessage("Seleccione Mensaje"));
        }

        else {
            int id = Integer.parseInt(actualUser.getIdToEliminate().substring(actualUser.getIdToEliminate().indexOf(":") + 1, actualUser.getIdToEliminate().indexOf("-")));
            for (Message mensaje : messagesToShow) {
                if (mensaje.getId() == id)
                    total_messages.remove(mensaje);
                    facesContext.addMessage("EliminarMessage", new FacesMessage("Mensaje Eliminado"));
            }
        }
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
            facesContext.addMessage("RegisterButton", new FacesMessage("Usuario YA Existente"));
            return "failure";
        }
        else {
            System.out.println("NO HAY entonces si PUEDE REgistrarse");
            registeredUsers.put(userName, new UserBean(userName, userPass, name, lastName));
            facesContext.addMessage("RegisterButton", new FacesMessage("REGISTRO EXITOSO LOGEESE"));
            return "success";
        }

    }

    public String validateUser(){
        FacesContext facesContext=FacesContext.getCurrentInstance();
        Map<String,String> parametros=facesContext.getExternalContext().getRequestParameterMap();

        String userName=parametros.get("userNameInputText");
        String userPass=parametros.get("passwordInputText");

        System.out.println("IMPRIMIO");
        System.out.println(userName);

        UserBean user=registeredUsers.get(userName);

        if (user==null){
            facesContext.addMessage("LoginButton", new FacesMessage("USUARIO NO EXISTE"));
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

    public String logout(){
        FacesContext facesContext=FacesContext.getCurrentInstance();
        Map<String,String> parametros=facesContext.getExternalContext().getRequestParameterMap();
        String userName=parametros.get("actualUser");

        onlineUsers.remove(userName);

        return "success";
    }

}
