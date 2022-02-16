package webservices;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/*
Consumer.java


Simple JMS consumer for Apache ActiveMQ

(c)2013 Kevin Boone
 */

// Note that the only Apache-specific class referred to in the source is
//  the one that provides the initial broker connection. The rest is
//  standard JMS
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
	private static final String NO_MESSAGE = "no message";

	private String clientId;
	private Connection connection;
	private MessageConsumer messageConsumer;

	public void create(String clientID) throws JMSException {
		this.clientId = clientID;
		
		// Create a connection factory referring to the broker host and port
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

		// Create a connection
		connection = factory.createConnection();
		connection.setClientID(clientID);
		connection.start();

		// Create a session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Create a topic
		Topic topic = session.createTopic("test_topic");

		messageConsumer = session.createDurableSubscriber(topic, clientID);
	}

	public void closeConnection() throws JMSException {
		connection.close();
	}

	public String getMessage(int timeout) throws JMSException {
		String result = NO_MESSAGE;

		// read a message from the topic destination
		Message message = messageConsumer.receive(timeout);

		// check if a message was received
		if (message != null) {
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();

			System.out.println(clientId + " received message: " + text);
			
			result = text;
		} else {
			System.out.println(clientId + " no message received");
		}
		return result;
	}

}
