package icc.referral.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import icc.referral.model.Course;
import icc.referral.model.Question;

public interface QuestionDao extends JpaRepository<Question, Long> {
	Question findById(Long id);
	
	List<Question> findByCourse(Course course);
	

	@Modifying
	@Query(value = "select distinct p.email from user u join profile p join question q where q.user_id=u.id and p.user_id=u.id and q.course_id=:courseid", nativeQuery = true)
	List<String> getEmailIDsFromCourses(@Param("courseid") long courseid);
}
