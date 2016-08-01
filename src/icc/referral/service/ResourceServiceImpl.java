package icc.referral.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import icc.referral.dao.CourseDao;
import icc.referral.dao.ResourceDao;
import icc.referral.model.Course;
import icc.referral.model.Resource;

@Service("resourceService")
@Transactional(propagation = Propagation.REQUIRED)
public class ResourceServiceImpl implements ResourceService {

	@javax.annotation.Resource
	private ResourceDao resourceDao;

	@javax.annotation.Resource
	private CourseDao courseDao;

	@Override
	public Resource addResource(Resource resource) {
		return resourceDao.save(resource);
	}

	@Override
	public List<Resource> getResources() {
		return resourceDao.findAll();
	}

	@Override
	public void delete(long id) {
		resourceDao.delete(id);
	}

	@Override
	public List<Resource> findByCourse(long id) {
		Course course = courseDao.findOne(id);
		return resourceDao.findByCourse(course);
	}

	@Override
	public List<String> getEmailIDsFromCourses(long id) {
		return resourceDao.getEmailIDsFromCourses(id);
	}

}
