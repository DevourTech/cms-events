package org.cms.events.apis.assignment.upload;

import org.cms.events.apis.Producer;

public interface AssignmentUploadProducer extends Producer {
	void produce(AssignmentUploadEvent event) throws AssignmentUploadEventException;
}
