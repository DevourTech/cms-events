package org.cms.events.assignment;

import org.cms.events.apis.assignment.upload.AssignmentUploadConsumer;
import org.cms.events.apis.assignment.upload.AssignmentUploadEventHandler;

public class AssignmentUploadConsumerImpl implements AssignmentUploadConsumer {

	private AssignmentUploadEventHandler handler;

	public void start() {}

	public void close() {}

	public void registerHandler(AssignmentUploadEventHandler handler) {
		this.handler = handler;
	}

	private class EventConsumer implements Runnable {

		public void run() {}
	}
}
