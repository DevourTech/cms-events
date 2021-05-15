package org.cms.events.apis.assignment;

import org.cms.core.files.assignment.Assignment;

public interface AssignmentUploadEventHandler {
	void handle(Assignment event);
}
