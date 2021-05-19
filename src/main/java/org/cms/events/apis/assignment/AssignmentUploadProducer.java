package org.cms.events.apis.assignment;

import org.cms.core.files.assignment.Assignment;
import org.cms.events.apis.Producer;

public interface AssignmentUploadProducer extends Producer {
	void produce(Assignment event) throws AssignmentUploadEventException;
}
