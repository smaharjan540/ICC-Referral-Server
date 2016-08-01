package icc.referral.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import icc.referral.dao.FacultyDao;
import icc.referral.model.Faculty;

@Service("facultyService")
@Transactional(propagation = Propagation.REQUIRED)
public class FacultyServiceImpl implements FacultyService {

	@Resource
	private FacultyDao facultyDao;

	@Override
	public Faculty addFaculty(Faculty faculty) {
		return facultyDao.save(faculty);
	}

	@Override
	public List<Faculty> getFaculties() {
		return facultyDao.findAll();
	}

	@Override
	public void delete(long id) {
		facultyDao.delete(id);
	}

	@Override
	public Map<Long, String> getFacultiesMap() {
		Map<Long, String> faculties = new LinkedHashMap<>();
		for (Faculty faculty : getFaculties()) {
			faculties.put(faculty.getId(), faculty.getName());
		}
		return faculties;

	}

	@Override
	public Faculty findById(long id) {
		return facultyDao.findOne(id);
	}

	@Override
	public List<String> getFacultyList() {
		List<String> faculties = new ArrayList<>();
		for (Faculty faculty : getFaculties()) {
			faculties.add(faculty.getName());
		}
		return faculties;
	}

}
