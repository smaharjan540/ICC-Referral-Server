package icc.referral.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import icc.referral.model.Referral;
import icc.referral.model.User;

public interface ReferralDao extends JpaRepository<Referral, Long> {

	List<Referral> findByUser(User user);

	@Modifying
	@Query(value = "select * from referral r where r.user_id in(select c.user_id from consultant c where c.recruiter_id=:recruiterid)", nativeQuery = true)
	List<Referral> getAllReferralsByRecruiterId(@Param("recruiterid") long recruiterid);

}
