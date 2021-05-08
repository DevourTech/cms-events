package org.cms.events.assignment;

import com.jsoniter.JsonIterator;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.cms.events.Event;
import org.cms.events.apis.assignment.upload.AssignmentUploadConsumer;
import org.cms.events.apis.assignment.upload.AssignmentUploadEvent;
import org.cms.events.apis.assignment.upload.AssignmentUploadEventHandler;
import org.cms.events.apis.config.ConsumerConfigs;

public class AssignmentUploadConsumerImpl implements AssignmentUploadConsumer {

	private AssignmentUploadEventHandler handler;
	private final ConsumerConfigs config;
	private ConsumingThread consumingThread;

	public AssignmentUploadConsumerImpl(ConsumerConfigs config) {
		this.config = config;
	}

	public void start() {
		consumingThread = new ConsumingThread(config.getProperties(), config.getTopic(), handler);
		consumingThread.start();
	}

	public void close() {
		consumingThread.close();
	}

	public void registerHandler(AssignmentUploadEventHandler handler) {
		this.handler = handler;
	}

	private static class ConsumingThread extends Thread {

		private final KafkaConsumer<String, String> kafkaConsumer;
		private final AssignmentUploadEventHandler eventHandler;
		private boolean notStopped;

		public ConsumingThread(Properties properties, String topic, AssignmentUploadEventHandler eventHandler) {
			kafkaConsumer = new KafkaConsumer<>(properties);
			kafkaConsumer.subscribe(Collections.singletonList(topic));
			this.eventHandler = eventHandler;
			notStopped = true;
		}

		@Override
		public void run() {
			while (notStopped) {
				ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
				for (ConsumerRecord<String, String> record : records) {
					if (!record.key().equals(Event.ASSIGNMENT_UPLOAD.name())) {
						continue;
					}
					AssignmentUploadEvent event = JsonIterator.deserialize(record.value(), AssignmentUploadEvent.class);
					eventHandler.handle(event);
				}
			}
		}

		public void close() {
			notStopped = false;
		}
	}
}
