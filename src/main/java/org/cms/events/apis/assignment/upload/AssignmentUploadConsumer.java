package org.cms.events.apis.assignment.upload;

import org.cms.events.apis.Consumer;

public interface AssignmentUploadConsumer extends Consumer {
	void registerHandler(AssignmentUploadEventHandler handler);
}
