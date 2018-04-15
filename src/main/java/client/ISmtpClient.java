package client;

import model.Message;

public interface ISmtpClient {

    public void SendMail(Message message);
}
