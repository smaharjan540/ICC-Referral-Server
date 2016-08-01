package icc.referral.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import icc.referral.model.User;

public interface UserService {
	User findByUsername(String username);
	
	User findByUsernamePassword(String username, String password);

	List<User> findAllAdminUsers();

	List<User> findAllUsers();

	List<User> findAllRecruiterUsers();

	void updateUserProfile(User user);

	void updateProfilePicture(String imageUrl, long id);

	List<User> findByUsernameOrEmail(String username, String email);

	User findById(long id);

	void updateUserPassword(String password, long id);

	User addUser(User user);

	void activateUser(boolean active, long id);

}