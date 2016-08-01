package icc.referral.service;

import java.util.List;

import icc.referral.model.Referral;
import icc.referral.model.User;

public interface ReferralService {

	public Referral addReferral(Referral referral);

	public List<Referral> getAllReferral();

	public Referral getReferralById(long id);

	void delete(long id);

	List<Referral> findByUser(User user);

	List<Referral> getAllReferralsByRecruiterId(long recruiterid);

}
