import client.SmtpClient;
import model.Message;
import utils.Parser;

import javax.naming.NameParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

public class MailRobot {

    private static final Logger LOG = Logger.getLogger(MailRobot.class.getName());
    private static String fileConfig = "src/main/resources/config.properties";
    private static String fileVictims = "src/main/resources/victims.utf8";
    private static String fileMessages = "src/main/resources/messages.utf8";

    public MailRobot(){
    }

    public void init(){

        Parser parser = new Parser();
        Properties properties = null;
        ArrayList<String> victims = new ArrayList<>();
        ArrayList<String> bodyMessages = new ArrayList<>();
        int r1 = (int)(Math.random() * 16 + 1);
        int r2 = (int)(Math.random() * 16 + 1);
        int r3 = (int)(Math.random() * 2 + 1);

        try {
            properties = parser.loadConfig(fileConfig);
            victims = parser.loadEMail(fileVictims);
            bodyMessages = parser.loadMessages(fileMessages);
            System.out.println("size = " + bodyMessages.size());

        } catch (IOException e){
            LOG.info(e.getMessage());
        }

        SmtpClient client = new SmtpClient(properties.getProperty("smtpServerAddress"), properties.getProperty("smtpServerPort"));

        Message m = new Message();

        m.setBody(bodyMessages.get(r3));
        m.setFrom(victims.get(r1));
        m.setSubject("prank");
        m.setTo(new String[]{victims.get(r2)});
        m.setCc(new String[]{victims.get(4), victims.get(5)});

        try {
            client.sendMessage(m);
        } catch(IOException e){
            LOG.info(e.getMessage());
        }

    }

    public static void main(String args[]){
        MailRobot mailRobot = new MailRobot();
        mailRobot.init();
    }
}
