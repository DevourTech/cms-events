package org.cms.events.factory;

import org.cms.events.apis.assignment.upload.AssignmentUploadConsumer;
import org.cms.events.apis.config.ConsumerConfigs;
import org.cms.events.assignment.AssignmentUploadConsumerImpl;

public class ConsumerFactory {

	public static AssignmentUploadConsumer newAssignmentUploadConsumer(ConsumerConfigs config) {
		return new AssignmentUploadConsumerImpl(config);
	}
	// TODO : Add more create functions for different types of producers
}
