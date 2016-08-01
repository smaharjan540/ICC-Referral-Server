package icc.referral.service;

import java.util.List;

import icc.referral.model.Question;

public interface QuestionService {

	public Question addQuestion(Question question);

	public List<Question> getAllQuestion();

	public Question getQuestionById(long id);
	
	void delete(long id);
	
	public List<Question> findByCourse(long id);
	
	List<String> getEmailIDsFromCourses(long id);

}
