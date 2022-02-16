package webservices;

/*

Producer.java

Simple JMS producer for Apache ActiveMQ

(c)2013 Kevin Boone
 */

// Note that the only Apache-specific class referred to in the source is
//  the one that provides the initial broker connection. The rest is
//  standard JMS
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

import javax.jms.*;

public class Producer {
	private Connection connection;
	private Session session;
	private MessageProducer messageProducer;

	public void create() throws Exception {
		// Create a connection factory referring to the broker host and port
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

		// Create a connection
		connection = factory.createConnection();
		connection.start();

		// Create a session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Create a topic
		Topic topic = session.createTopic("test_topic");

		messageProducer = session.createProducer(topic);
	}

	public void closeConnection() throws JMSException {
		connection.close();
	}

	public void sendMessage(String message) throws JMSException {
		// create a JMS TextMessage
		TextMessage textMessage = session.createTextMessage(message);

		// send the message to the topic destination
		messageProducer.send(textMessage);
		
		System.out.println("Sent message: \"" + message + "\"");
	}
}
