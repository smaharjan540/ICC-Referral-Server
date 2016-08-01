package icc.referral.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import icc.referral.dao.CourseDao;
import icc.referral.dao.QuestionDao;
import icc.referral.model.Course;
import icc.referral.model.Question;

@Service("questionService")
@Transactional(propagation = Propagation.REQUIRED)
public class QuestionServiceImpl implements QuestionService {

	@Resource
	private QuestionDao questionDao;

	@Resource
	private CourseDao courseDao;

	@Override
	public Question addQuestion(Question question) {
		return questionDao.save(question);
	}

	@Override
	public List<Question> getAllQuestion() {
		return questionDao.findAll();
	}

	@Override
	public Question getQuestionById(long id) {
		return questionDao.findOne(id);
	}

	@Override
	public void delete(long id) {
		questionDao.delete(id);

	}

	@Override
	public List<Question> findByCourse(long id) {
		Course course = courseDao.findOne(id);
		return questionDao.findByCourse(course);
	}

	@Override
	public List<String> getEmailIDsFromCourses(long id) {
		return questionDao.getEmailIDsFromCourses(id);
	}

}
