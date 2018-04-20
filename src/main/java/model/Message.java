/**
 * Message class.
 * @author Romain Gallay
 * @author Labinot Rashiti
 */
package model;

public class Message {

    private String from;
    private String[] to;
    private String body;

    public Message() {}
    
    public Message(String from, String[] to, String body) {
       this.from = from;
       this.to = to;
       this.body = body;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void signMessage(Person p){
        body += "\r\n" + p.getFirstname() + " " + p.getLastname();
    }
}
