# RES-Labo-03-SMTP

## Description of the project

This project is about generating and sending forged e-mails. MailRobot is the Java TCP client who will randomly choose a set of e-mails (the sender and the victims), a set of messages and send them to the victims.

The data is provided by the resource files "victims.utf8" for the list of e-mails and "messages.utf8" for the list of body messages. There is also a configuration file "config.properties" which contains the following useful informations :

- smtpServeurAddress : IP of the SMTP server
- smtpServeurPort : Port of the SMTP server
- numberOfGroups : The number of groups of victims

Notice that the minimum number of victims by group is three (one sender and at least two recipients). 

## MockMock STMP server with Docker

If you don't want to send the forged e-mails to a real SMTP server and get banned, just use MockMock !

[MockMock](https://github.com/tweakers-dev/MockMock) is a cross-platform SMTP server built on Java. Thanks to MockMock, you can do some tests with the MailRobot. MockMock will listen, get the e-mails sent and show them on a web interface. 

We invite you to combine MockMock with Docker. By this way, you can have a docker container who runs your MockMock software. To do this, you have to install [Docker](https://www.docker.com/), get our repo (wich contains the MockMock serveur and the Dockerfile) and do these steps : 

- Launch a Docker terminal
- go to the "docker-server" directory
- Create the docker image with this command : ```docker build -t res-mockmock-smtp .``` 
- Run the container with this command : ```docker run -p 2525:2525 -p 8282:8282 res-mockmock-smtp```

We chose the 2525 port for SMTP, and the 8282 port is the default port fort the MockMock web interface. If you want to change either of them, just be careful with the Dockerfile configuration and the "config.properties" file.

## Let's prank !

Now that you have installed and launched your MockMock container, you can run and use our MailRobot software !

If you want to see the results in the MockMock, just type ```192.168.99.100:8282``` in your favorite browser !





