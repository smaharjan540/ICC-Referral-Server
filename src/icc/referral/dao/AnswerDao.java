package icc.referral.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import icc.referral.model.Answer;
import icc.referral.model.Question;
import icc.referral.model.User;

public interface AnswerDao extends JpaRepository<Answer, Long> {

	/*
	 * @Modifying
	 * 
	 * @Query(
	 * "update mum.cs544.model.Answer set mum.cs544.model.User=:user,mum.cs544.model.Question=:question where id=:id"
	 * ) void updateAnswerResult(@Param("user") User user, @Param("question")
	 * Question question, @Param("id") long id);
	 */
	@Modifying
	@Query(value = "update answer set user_id=:userid,question_id=:questionid where id=:id", nativeQuery = true)
	void updateAnswerResult(@Param("userid") long user_id, @Param("questionid") long question_id, @Param("id") long id);

	@Modifying
	@Query(value = "select distinct p.email from user u join profile p join question q where q.user_id=u.id and p.user_id=u.id and q.course_id=:courseid", nativeQuery = true)
	List<String> getEmailIDsFromCourses(@Param("courseid") long courseid);
}