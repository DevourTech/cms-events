package org.cms.events.assignment;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.cms.core.files.assignment.Assignment;
import org.cms.events.apis.assignment.AssignmentUploadEventException;
import org.cms.events.apis.assignment.AssignmentUploadProducer;
import org.cms.events.apis.config.ProducerConfigs;

public class AssignmentUploadProducerImpl implements AssignmentUploadProducer {

	private final ProducerConfigs config;

	public AssignmentUploadProducerImpl(ProducerConfigs config) {
		this.config = config;
	}

	public void start() {
		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(config.getProperties());
	}

	public void close() {}

	public void produce(Assignment event) throws AssignmentUploadEventException {}
}
