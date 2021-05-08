package org.cms.events.apis.assignment.upload;

import java.io.Serializable;
import org.cms.core.course.Course;
import org.cms.core.instructor.Instructor;

public class AssignmentUploadEvent implements Serializable {

	Instructor instructor;
	String downloadPath;
	Course course;

	public AssignmentUploadEvent() {}

	public AssignmentUploadEvent(Instructor instructor, String downloadPath, Course course) {
		this.instructor = instructor;
		this.downloadPath = downloadPath;
		this.course = course;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
