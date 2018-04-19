package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class PrankGenerator {

   private ArrayList<Prank> pranks;
   private int numberOfGroups;
   ArrayList<String> messages;
   ArrayList<String> emails;

    private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());

   public PrankGenerator(int numberOfGroups, ArrayList<String> messages, ArrayList<String> emails) {
      this.emails = emails;
      this.numberOfGroups = numberOfGroups;
      this.messages = messages;
      pranks = new ArrayList<>();
   }


    public void generatePrank() {

        int numberOfVictims = emails.size();

       if( numberOfVictims / numberOfGroups < 3) {
           numberOfGroups = numberOfVictims / 3;
           LOG.warning("Not enough victims to generate the number of groups asked. The number of groups will be "
                   + numberOfGroups);
       }

        List<Group> groups = generateGroups(new Group(emails));

       int index = 0;

       for(Group group : groups){

           Prank prank = new Prank();
           Message message = new Message();

           // shuffle the group, take the 1st member as sender and the other as receiver
           group.shuffleMembers();
           Person sender = group.removePerson(0);
           prank.setSender(sender);
           prank.setReceivers(group);

           // create a new message
           message.setFrom(prank.getSender().getEmail());
           message.setTo(prank.getReceivers().membersEmail());
           message.setBody(messages.get(index));
           message.signMessage(sender);

           // increment index to take a different message for the next group
           index = (index + 1)%messages.size();

           prank.setMessage(message);
           pranks.add(prank);

       }
   }

   public List<Group> generateGroups(Group victims) {

      victims.shuffleMembers();
      List<Group> groups = new ArrayList<>();

      // create a list of groups
      for (int i=0; i< numberOfGroups; i++) {
         Group group = new Group();
         groups.add(group);
      }

      int groupNumber = 0;

      // iterate on victims and add them in each group
      while (victims.size() > 0) {
         groups.get(groupNumber).addMember(victims.removePerson(0));
         groupNumber = (groupNumber + 1) % numberOfGroups;
      }

      return groups;
   }


   public static int generateRandomNumber(int upper, int lower) {
      return (int) (Math.random() * (upper - lower)) + lower;
   }


    public ArrayList<Prank> getPranks() {
        return pranks;
    }

    public void setPranks(ArrayList<Prank> pranks) {
        this.pranks = pranks;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public void setNumberOfGroups(int numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }
}
