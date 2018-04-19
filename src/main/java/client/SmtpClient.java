/**
 * StmpClient class.
 * @author Romain Gallay
 * @author Labinot Rashiti
 */
package client;

import model.*;
import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class SmtpClient {
   
   // Init part
   private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
   Socket clientSocket = null;
   BufferedReader reader = null;
   PrintWriter writer = null;

   private String line;
   private String address;
   private int port;

   /**
    * Constructor
    * @param address ip address
    * @param port port number
    */
   public SmtpClient(String address, String port) {
      this.address = address;
      this.port = Integer.parseInt(port);
   }

   /**
    * send a stmp message.
    * @param message
    * @throws IOException
    */
   public void sendMessage(Message message) throws IOException {
      clientSocket = new Socket(address, port);
      writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
      reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
      read();

      // start by sending EHLO
      write("EHLO" + address);

      // get the 250 message
      while (line.startsWith("250")) {
         read();
      }

      // send from
      write("MAIL FROM: <" + message.getFrom() + ">");
      read();

      // send to
      for (String to : message.getTo()) {
         write("RCPT TO: <" + to + ">");
         read();
      }

      // send data
      write("DATA");
      read();

      // send the charset and the core of the message
      write("Content-Type: text/plain; charset=UTF-8");
      write("From: " + message.getFrom());
      write("To: " + String.join(",", message.getTo()));

      write(message.getBody());
      write(".");
      read();

      // close streams and socket
      clean();

   }

   /**
    * read the answer from server and log it.
    * @throws IOException 
    */
   public void read() throws IOException {
      line = reader.readLine();
      LOG.info(line);
   }

   /**
    * write a request to the server.
    * @param request message to server
    * @throws IOException 
    */
   public void write(String request) throws IOException {
      writer.println(request);
      writer.flush();
      LOG.info(request);
   }

   /**
    * closes all the variables.
    * @throws IOException 
    */
   public void clean() throws IOException {
      writer.close();
      reader.close();
      clientSocket.close();
   }

   /*
    public static void main (String args[]) throws IOException{
        Message m = new Message();

        m.setBody("Hi mate,\r\nI hope you doing well\r\nTake care\r\n\r\nBob");
        m.setFrom("tom@tommy.io");
        m.setSubject("well hello there");
        m.setTo(new String[]{"bob@bobbythebest.bestest"});
        m.setCc(new String[]{"hiddenspy@spy.spy"});

        SmtpClient client = new SmtpClient("localhost", 2525);
        client.SendMessage(m);

    }*/
}
