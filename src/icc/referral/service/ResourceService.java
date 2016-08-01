package icc.referral.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

import icc.referral.model.Course;
import icc.referral.model.Resource;

public interface ResourceService {

	Resource addResource(Resource resource);

	List<Resource> getResources();

	void delete(long id);

	List<Resource> findByCourse(long id);

	List<String> getEmailIDsFromCourses(long id);

}
