package icc.referral.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import icc.referral.dao.RoleDao;
import icc.referral.dao.UserDao;
import icc.referral.model.Admin;
import icc.referral.model.Consultant;
import icc.referral.model.Professor;
import icc.referral.model.Recruiter;
import icc.referral.model.Role;
import icc.referral.model.School;
import icc.referral.model.Student;
import icc.referral.model.User;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED)//User
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userdao;

	@Resource
	private RoleDao roledao;

	@Override
	public User findByUsername(String username) {
		return userdao.findByUsername(username);
	}

	@Override
	public List<User> findAllUsers() {
		return (List<User>) userdao.findAll();
	}

	@Override
	public void updateUserProfile(User user) {

	}

	@Override
	public List<User> findByUsernameOrEmail(String username, String email) {
		return userdao.findByUsernameOrEmail(username, email);
	}

	@Override
	public User findById(long id) {
		return userdao.findById(id);
	}

	@Override
	public void updateUserPassword(String password, long id) {
		userdao.updateUserPassword(password, id);

	}

	@Override
	public void activateUser(boolean active, long id) {
		userdao.activateUser(active, id);

	}

	@Override
	public List<User> findAllSchoolUsers() {
		return userdao.findAllSchoolUsers();
	}

	@Override
	public List<User> findAllAdminUsers() {
		return userdao.findAllAdminUsers();
	}

	@Override
	public List<User> findAllProfessorUsers() {
		return userdao.findAllProfessorUsers();
	}

	private static final int PAGE_SIZE = 3;

	@Override
	public List<User> findAllStudentUsers() {
		return userdao.findAllStudentUsers();
	}

	@Override
	public Page<User> getProfessorUsers(Integer pageNumber) {
		

		PageRequest pageable = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
		return userdao.findAll(pageable);
	}

	@Override
	public User addUser(User user) {

		Role role = null;
		if (user instanceof School) {
			role = roledao.findByType("SCHOOL");
			user = (School) user;
		} else if (user instanceof Admin) {
			role = roledao.findByType("ADMIN");
			user = (Admin) user;
		} else if (user instanceof Professor) {
			role = roledao.findByType("PROFESSOR");
			user = (Professor) user;
		} else if (user instanceof Student) {
			role = roledao.findByType("STUDENT");
			user = (Student) user;
		} else if (user instanceof Recruiter) {
			role = roledao.findByType("RECRUITER");
			user = (Recruiter) user;
		} else if (user instanceof Consultant) {
			role = roledao.findByType("CONSULTANT");
			user = (Consultant) user;
		}

		user.addRole(role);
		user.setActive(true);

		return userdao.save(user);
	}

	@Override
	public void updateProfilePicture(String imageUrl, long id) {

	}

	@Override
	public Map<Long, String> getProfessorsMap() {
		Map<Long, String> faculties = new LinkedHashMap<>();
		for (User user : findAllProfessorUsers()) {
			faculties.put(user.getId(), user.getFullName());
		}
		return faculties;
	}

	@Override
	public User findByUsernamePassword(String username, String password) {
		return userdao.findByUsernameAndPassword(username, password);
	}

	@Override
	public List<User> findAllRecruiterUsers() {
		return userdao.findAllRecruiterUsers();
	}

}
