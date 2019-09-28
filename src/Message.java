import java.util.Date;

public class Message {
    private String content;
    private UserBean destinatario;
    private UserBean remitente;
    private String fecha;

    public Message(String content, UserBean destinatario, UserBean remitente, String fecha){
        this.content=content;
        this.destinatario=destinatario;
        this.fecha=fecha;
        this.remitente=remitente;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserBean getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(UserBean destinatario) {
        this.destinatario = destinatario;
    }

    public UserBean getRemitente() {
        return remitente;
    }

    public void setRemitente(UserBean remitente) {
        this.remitente = remitente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
