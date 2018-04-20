/**
 * MailRobot class.
 * @author Romain Gallay
 * @author Labinot Rashiti
 */
import client.SmtpClient;
import model.Message;
import model.Person;
import model.Prank;
import utils.Parser;

import javax.naming.NameParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;
import model.PrankGenerator;

public class MailRobot {

   // configuration
   private static final Logger LOG = Logger.getLogger(MailRobot.class.getName());
   private static String fileConfig = "src/main/resources/config.properties";
   private static String fileVictims = "src/main/resources/victims.utf8";
   private static String fileMessages = "src/main/resources/messages.utf8";
   private final int nbGroups = 3;

   public MailRobot() {
   }

   public void init() {

      // initialisation
      Parser parser = new Parser();
      Properties properties = null;
      ArrayList<String> victims = new ArrayList<>();
      ArrayList<String> bodyMessages = new ArrayList<>();

      // get config from files
      try {
         properties = parser.loadConfig(fileConfig);
         victims = parser.loadEMail(fileVictims);
         bodyMessages = parser.loadMessages(fileMessages);

      } catch (IOException e) {
         LOG.warning(e.getMessage());
      }

      SmtpClient client = new SmtpClient(properties.getProperty("smtpServerAddress"), properties.getProperty("smtpServerPort"));
      PrankGenerator prankGenerator = new PrankGenerator(nbGroups, bodyMessages, victims);
      prankGenerator.generatePrank();

      ArrayList<Prank> pranks = prankGenerator.getPranks();

      // send the pranks
      try {
         for (Prank p : pranks) {
            client.sendMessage(p.getMessage());
         }
      } catch (IOException e) {
         LOG.info(e.getMessage());
      }

   }

   public static void main(String args[]) {

      MailRobot mailRobot = new MailRobot();
      mailRobot.init();

   }
}
