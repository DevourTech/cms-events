package org.cms.events.apis.assignment;

import org.cms.core.course.Course;

public interface AssignmentUploadEventFilter {
	boolean satisfies(Course course);
}
