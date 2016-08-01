package icc.referral.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import icc.referral.dao.ReferralDao;
import icc.referral.model.Referral;
import icc.referral.model.User;

@Service("referralService")
@Transactional(propagation = Propagation.REQUIRED)
public class ReferralServiceImpl implements ReferralService {

	@Resource
	private ReferralDao referralDao;

	@Override
	public Referral addReferral(Referral referral) {
		return referralDao.save(referral);
	}

	@Override
	public List<Referral> getAllReferral() {
		return referralDao.findAll();
	}

	@Override
	public Referral getReferralById(long id) {
		return referralDao.findOne(id);
	}

	@Override
	public void delete(long id) {
		referralDao.delete(id);

	}

	@Override
	public List<Referral> findByUser(User user) {
		return referralDao.findByUser(user);
	}

	@Override
	public List<Referral> getAllReferralsByRecruiterId(long recruiterid) {
		return referralDao.getAllReferralsByRecruiterId(recruiterid);
	}

	@Override
	public Referral requestUpdateReferral(Referral referral) {
		return referralDao.save(referral);
	}

}
