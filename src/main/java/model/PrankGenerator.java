package model;

import java.util.ArrayList;
import java.util.Collections;

public class PrankGenerator {

   private ArrayList<Message> pranks;
   private int numberOfGroups;
   ArrayList<String> messages;
   ArrayList<String> emails;

   public PrankGenerator(int numberOfGroups, ArrayList<String> messages, ArrayList<String> emails) {
      this.emails = emails;
      this.numberOfGroups = numberOfGroups;
      this.messages = messages;
      pranks = new ArrayList<>();
   }

   public void generate() {

      for (int i = 0; i < numberOfGroups; i++) {
         ArrayList<String> victims = new ArrayList<>();
         int numberOfVictims = generateRandomNumber(3, emails.size());
         Collections.shuffle(emails);

         for (int j = 0; j < numberOfVictims; ++j) {
            victims.add(emails.get(j));
         }

         int messageNumber = generateRandomNumber(1, messages.size()) - 1;
         Message message = new Message(victims.remove(0), victims.toArray(new String[victims.size()]), new String[]{""}, messages.get(messageNumber));
         pranks.add(message);
      }
   }

   public ArrayList<Message> getPranks() {
      return pranks;
   }

   public static int generateRandomNumber(int upper, int lower) {
      return (int) (Math.random() * (upper - lower)) + lower;
   }
}
