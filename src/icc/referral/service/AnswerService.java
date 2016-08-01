package icc.referral.service;

import java.util.List;

import icc.referral.model.Answer;
import icc.referral.model.Question;
import icc.referral.model.User;

public interface AnswerService {

	public Answer addAnswer(Answer answer);

	public List<Answer> getAllAnswer();

	public Answer getAnswerById(long id);

	void delete(long id);

	void updateAnswerResult(long user_id,long question_id, long id);
	
	List<String> getEmailIDsFromCourses(long id);

}
