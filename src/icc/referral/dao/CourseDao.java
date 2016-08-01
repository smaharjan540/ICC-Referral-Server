package icc.referral.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import icc.referral.model.Course;
import icc.referral.model.Faculty;

public interface CourseDao extends JpaRepository<Course, Long> {

	List<Course> findByFaculty(Faculty faculty);

	List<Course> findByIdIn(List<Long> ids);

	/*@Modifying
	@Query("select c from mum.cs544.model.Course c join mum.cs544.model.Student s where s.username=:username")
	List<Course> getCoursesByStudent(@Param("username") String username);*/

}
