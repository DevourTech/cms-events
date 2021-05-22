package org.cms.events.assignment;

import com.jsoniter.JsonIterator;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cms.core.course.Course;
import org.cms.core.files.assignment.Assignment;
import org.cms.events.Event;
import org.cms.events.apis.assignment.AssignmentUploadConsumer;
import org.cms.events.apis.assignment.AssignmentUploadEventFilter;
import org.cms.events.apis.assignment.AssignmentUploadEventHandler;
import org.cms.events.apis.config.ConsumerConfigs;

public class AssignmentUploadConsumerImpl implements AssignmentUploadConsumer {

	private AssignmentUploadEventHandler handler;
	private AssignmentUploadEventFilter filter;
	private final ConsumerConfigs config;
	private ConsumingThread consumingThread;

	public AssignmentUploadConsumerImpl(ConsumerConfigs config) {
		this.config = config;
	}

	public void start() {
		consumingThread = new ConsumingThread(config.getProperties(), config.getTopic(), handler, filter);
		consumingThread.start();
	}

	public void close() {
		consumingThread.close();
	}

	public void registerHandler(AssignmentUploadEventHandler handler) {
		this.handler = handler;
	}

	@Override
	public void registerFilter(AssignmentUploadEventFilter filter) {
		this.filter = filter;
	}

	private static class ConsumingThread extends Thread {

		private final KafkaConsumer<String, String> kafkaConsumer;
		private final AssignmentUploadEventHandler eventHandler;
		private final AssignmentUploadEventFilter filter;
		private boolean notStopped;
		private final Logger logger = LogManager.getLogger(ConsumingThread.class);

		public ConsumingThread(
			Properties properties,
			String topic,
			AssignmentUploadEventHandler eventHandler,
			AssignmentUploadEventFilter filter
		) {
			kafkaConsumer = new KafkaConsumer<>(properties);
			kafkaConsumer.subscribe(Collections.singletonList(topic));
			this.eventHandler = eventHandler;
			this.filter = filter;
			notStopped = true;
		}

		@Override
		public void run() {
			while (notStopped) {
				ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
				for (ConsumerRecord<String, String> record : records) {
					Assignment event = JsonIterator.deserialize(record.value(), Assignment.class);
					Course course = event.getCourse();
					logger.info("Event received - " + course);
					if (filter.satisfies(course)) {
						logger.info("Sending event to handler");
						eventHandler.handle(event);
					}
				}
			}
		}

		public void close() {
			notStopped = false;
		}
	}
}
