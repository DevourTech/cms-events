package org.cms.events;

import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.cms.events.apis.assignment.upload.AssignmentUploadConsumer;
import org.cms.events.apis.assignment.upload.AssignmentUploadEventHandler;
import org.cms.events.apis.config.ConsumerConfigs;
import org.cms.events.factory.ConsumerFactory;
import org.junit.jupiter.api.Test;

public class AssignmentUploadConsumerTest {

	@Test
	public void testConsumer() throws InterruptedException {
		ConsumerConfigs consumerConfigs = new ConsumerConfigs(getProperties(), "sample");
		AssignmentUploadConsumer consumer = ConsumerFactory.newAssignmentUploadConsumer(consumerConfigs);
		consumer.registerHandler(getHandler());
		consumer.start();
		Thread.sleep(5000);
		consumer.close();
	}

	private AssignmentUploadEventHandler getHandler() {
		return event -> {
			System.out.println(event.getInstructor());
			System.out.println(event.getCourse());
			System.out.println(event.getDownloadPath());
		};
	}

	private Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "lol");
		return properties;
	}
}
