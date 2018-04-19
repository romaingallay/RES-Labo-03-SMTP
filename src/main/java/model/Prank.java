package model;

public class Prank {

    private Person sender;
    private Group receivers;
    private Message message;

    public Prank(){}

    public Prank(Person sender, Group receivers) {
        this.sender = sender;
        this.receivers = receivers;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public void setReceivers(Group receivers) {
        this.receivers = receivers;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Person getSender() {
        return sender;
    }

    public Group getReceivers() {
        return receivers;
    }

    public Message getMessage() {
        return message;
    }
}
