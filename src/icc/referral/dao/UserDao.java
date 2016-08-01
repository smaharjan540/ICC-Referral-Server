package icc.referral.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import icc.referral.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	User findByUsername(String username);

	User findById(long id);

	@Modifying
	@Query("select a from icc.referral.model.Admin a")
	List<User> findAllAdminUsers();


	@Modifying
	@Query("select r from icc.referral.model.Recruiter r")
	List<User> findAllRecruiterUsers();

	@Modifying
	@Query("update icc.referral.model.User set firstname=:firstname,lastname=:lastname,email=:email where id=:id")
	void updateUserProfile(@Param("firstname") String firstname, @Param("lastname") String lastname,
			@Param("email") String email, @Param("id") long id);

	List<User> findByUsernameOrEmail(String username, String email);

	@Modifying
	@Query("update icc.referral.model.User set password=:password where id=:id")
	void updateUserPassword(@Param("password") String password, @Param("id") long id);

	@Modifying
	@Query("update icc.referral.model.User set active=:active where id=:id")
	void activateUser(@Param("active") boolean active, @Param("id") long id);

	User findByUsernameAndPassword(String username, String password);

}
