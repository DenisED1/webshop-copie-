package org.frontend;

import javax.servlet.ServletContextEvent;

import org.apache.activemq.broker.BrokerService;

import webservices.Consumer;
import webservices.Producer;
import webservices.Producers;

public class Listener implements javax.servlet.ServletContextListener {	
	private Producer testProducer;
	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		System.out.println("test");
		try {
			BrokerService broker = new BrokerService();

			broker.addConnector("tcp://localhost:61616");

			broker.start();

			System.out.println("Broker started");
			
			//Create a producer
			testProducer = Producers.getTestProducer();
			testProducer = new Producer();
			testProducer.create();
			Producers.setTestProducer(testProducer);
			
			//Create a consumer
			Consumer testConsumer = new Consumer();
			testConsumer.create("testConsumer");
			
			Producers.getTestProducer().sendMessage("test");
			
			testConsumer.getMessage(1000);
			
		} catch (Exception e) {
			System.out.println("Broker could not be created");
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}
}