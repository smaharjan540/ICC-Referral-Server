package icc.referral.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import icc.referral.model.Course;
import icc.referral.model.Resource;
import icc.referral.model.User;

public interface ResourceDao extends JpaRepository<Resource, Long> {

	List<Resource> findByUser(User user);

	List<Resource> findByCourse(Course course);

	@Modifying
	@Query(value = "select distinct p.email from user u join profile p join resource r where u.id=r.user_id and u.id=p.user_id and r.course_id=:courseid", nativeQuery = true)
	List<String> getEmailIDsFromCourses(@Param("courseid") long courseid);

}
