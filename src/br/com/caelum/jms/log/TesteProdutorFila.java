package br.com.caelum.jms.log;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

import static javax.jms.DeliveryMode.NON_PERSISTENT;

public class TesteProdutorFila {

    public static void main(String[] args) throws Exception {
        InitialContext context = new InitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = connectionFactory.createConnection("user", "senha");
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination fila = (Destination) context.lookup("LOG");

        MessageProducer producer = session.createProducer(fila);
        Message message = session.createTextMessage("WORN | deu zica");
        producer.send(message, NON_PERSISTENT, 7, 80000);

//        for (int i = 0; i < 1000; i++) {
//
//            Message message = session.createTextMessage("<pedido><id>" + i + "</id></pedido>");
//            producer.send(message);
//
//        }


        new Scanner(System.in).next();

        session.close();
        connection.close();
        context.close();
    }
}
