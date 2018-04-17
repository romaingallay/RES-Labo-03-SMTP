package model;

public class Message {

    private String from;
    private String[] to;
    private String[] cc;
    private String body;

    public Message() {}
    
    public Message(String from, String[] to, String[] cc, String body) {
       this.from = from;
       this.to = to;
       this.cc = cc;
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

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
