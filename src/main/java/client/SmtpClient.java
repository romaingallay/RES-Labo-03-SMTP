package client;

import model.*;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class SmtpClient {

    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    Socket clientSocket = null;
    BufferedReader reader = null;
    PrintWriter writer = null;
    private String line;

    private String address;
    private int port;

    public SmtpClient(String address, int port){
        this.address = address;
        this.port = port;
    }

    public void SendMessage(Message message) throws IOException{

        clientSocket = new Socket(address, port);
        writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));

        readAnswer();

        writer.println("EHLO" + address);

        while(line.startsWith("250")){
            readAnswer();
        }

        writer.println("MAIL FROM: <" + message.getFrom() + ">");
        writer.flush();
        readAnswer();

        for(String to : message.getTo()){
            writer.println("RCPT TO: <" + to + ">");
            writer.flush();
            readAnswer();
        }

        for (String cc : message.getCc()){
            writer.println("RCPT TO: <" + cc + ">");
            writer.flush();
            readAnswer();
        }

        writer.println("DATA");
        writer.flush();
        readAnswer();

        writer.println("Content-Type: text/plain; charset=UTF-8");
        writer.println("From: " + message.getFrom());
        writer.println("To: " + String.join(",", message.getTo()));

        if(message.getCc().length > 0){
            writer.println("Cc: " + String.join(",", message.getCc()));
        }

        writer.println("Subject: " + message.getSubject());
        writer.println(message.getBody());
        writer.println(".");
        writer.flush();

        readAnswer();

        writer.close();
        reader.close();
        clientSocket.close();

    }

    public void readAnswer() throws IOException{
        line = reader.readLine();
        LOG.info(line);
    }


    public static void main (String args[]) throws IOException{
        Message m = new Message();

        m.setBody("Hi mate,\r\nI hope you doing well\r\nTake care\r\n\r\nBob");
        m.setFrom("tom@tommy.io");
        m.setSubject("well hello there");
        m.setTo(new String[]{"bob@bobbythebest.bestest"});
        m.setCc(new String[]{"hiddenspy@spy.spy"});

        SmtpClient client = new SmtpClient("localhost", 2525);
        client.SendMessage(m);

    }
}
