package model;

public class Mail {

    private Group group;
    private Message message;
    
    public Mail(Group group, Message message) {
       this.group = group;
       this.message = message;
    }
    
    public Group getGroup() {
       return group;
    }
    
    public Message getMessage() {
       return message;
    }
}
