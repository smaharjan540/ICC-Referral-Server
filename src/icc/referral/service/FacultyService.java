package icc.referral.service;

import java.util.List;
import java.util.Map;

import icc.referral.model.Faculty;

public interface FacultyService {

	Faculty addFaculty(Faculty faculty);

	List<Faculty> getFaculties();

	void delete(long id);

	public Map<Long, String> getFacultiesMap();

	List<String> getFacultyList();

	Faculty findById(long id);

}
