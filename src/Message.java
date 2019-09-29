import java.util.Date;

public class Message {
    private String content;
    private String destinatario;
    private String remitente;
    private String fecha;
    private int id;

    public Message(int id, String content, String destinatario, String remitente, String fecha){
        this.id=id;
        this.content=content;
        this.destinatario=destinatario;
        this.fecha=fecha;
        this.remitente=remitente;
    }

    public Message(){}


    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario (String destinatario) {
        this.destinatario = destinatario;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString(){
        return "id:"+ id +"-" + content +" " + " dest:"+ destinatario + " "+ " rem:"+ remitente + " "+ " fecha:" +fecha;
    }
}
