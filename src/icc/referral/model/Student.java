package icc.referral.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends User {

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "student_course", joinColumns = { @JoinColumn(name = "student_id") }, inverseJoinColumns = {
			@JoinColumn(name = "course_id") })
	private Set<Course> courses = new HashSet<>();

	@Transient
	private List<Long> coursesids = new ArrayList<>();

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public List<Long> getCoursesids() {
		return coursesids;
	}

	public void setCoursesids(List<Long> coursesids) {
		this.coursesids = coursesids;
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}

	public void addCourses(List<Course> courses) {
		this.courses.addAll(courses);
	}

}
