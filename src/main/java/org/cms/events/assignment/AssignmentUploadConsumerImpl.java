package org.cms.events.assignment;

import com.jsoniter.JsonIterator;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.cms.events.apis.assignment.upload.AssignmentUploadConsumer;
import org.cms.events.apis.assignment.upload.AssignmentUploadEvent;
import org.cms.events.apis.assignment.upload.AssignmentUploadEventHandler;
import org.cms.events.apis.config.ConsumerConfigs;

public class AssignmentUploadConsumerImpl implements AssignmentUploadConsumer {

	private AssignmentUploadEventHandler handler;
	private final ConsumerConfigs config;
	private Thread consumingThread;

	public AssignmentUploadConsumerImpl(ConsumerConfigs config) {
		this.config = config;
	}

	public void start() {
		consumingThread = new Thread(new EventConsumer(config.getProperties(), config.getTopic(), handler));
		consumingThread.start();
	}

	public void close() {
		consumingThread.interrupt();
	}

	public void registerHandler(AssignmentUploadEventHandler handler) {
		this.handler = handler;
	}

	private static class EventConsumer implements Runnable {

		private final KafkaConsumer<String, String> kafkaConsumer;
		private final AssignmentUploadEventHandler eventHandler;

		public EventConsumer(Properties properties, String topic, AssignmentUploadEventHandler eventHandler) {
			kafkaConsumer = new KafkaConsumer<>(properties);
			kafkaConsumer.subscribe(Arrays.asList(topic));
			this.eventHandler = eventHandler;
		}

		public void run() {
			while (true) {
				ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
				for (ConsumerRecord<String, String> record : records) {
					AssignmentUploadEvent event = JsonIterator.deserialize(record.value(), AssignmentUploadEvent.class);
					eventHandler.handle(event);
				}
			}
		}
	}
}
