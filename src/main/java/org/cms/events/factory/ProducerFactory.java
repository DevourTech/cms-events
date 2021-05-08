package org.cms.events.factory;

import org.cms.events.apis.assignment.upload.AssignmentUploadProducer;
import org.cms.events.apis.config.ProducerConfigs;
import org.cms.events.assignment.AssignmentUploadProducerImpl;

public class ProducerFactory {

	public static AssignmentUploadProducer newAssignmentUploadProducer(ProducerConfigs config) {
		return new AssignmentUploadProducerImpl(config);
	}
	// TODO : Add more create functions for different types of producers
}
