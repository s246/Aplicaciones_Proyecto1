import java.util.Date;

public class Message {
    private String content;
    private UserBean destinatario;
    private UserBean remitente;
    private Date fecha;

    public Message(String content, UserBean destinatario, UserBean remitente, Date fecha){
        this.content=content;
        this.destinatario=destinatario;
        this.fecha=fecha;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
