package utils;

import model.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Parser {

    public Parser(){}

    public ArrayList<String> loadMessages(String fileName) throws IOException{

        ArrayList<String> messages = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        String message = "";

        while((line = reader.readLine()) != null){

            message += line;
            message += "\r\n";

            if(line.startsWith("==")) {
                messages.add(message);
                message = "";
            }
        }
        messages.add(message);

        return messages;
    }

    public Properties loadConfig(String fileName) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
        Properties properties = new Properties();
        properties.load(reader);
        return properties;
    }

    public ArrayList<String> loadEMail(String fileName) throws IOException {

        ArrayList<String> emails = new ArrayList<>();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        while((line = reader.readLine()) != null){
            emails.add(line);
        }

        return emails;
    }
}
