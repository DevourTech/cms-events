package org.cms.events.apis.assignment;

import org.cms.events.apis.Consumer;

public interface AssignmentUploadConsumer extends Consumer {
	void registerHandler(AssignmentUploadEventHandler handler);
	void registerFilter(AssignmentUploadEventFilter filter);
}
