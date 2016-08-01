package icc.referral.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import icc.referral.dao.QueuedEmailDao;
import icc.referral.model.QueuedEmail;

@Service("emailService")
@Transactional(propagation = Propagation.REQUIRED)
public class QueuedEmailServiceImpl implements QueuedEmailService {

	@Resource
	private QueuedEmailDao emailDao;

	@Override
	public QueuedEmail addEmail(QueuedEmail email) {
		return emailDao.save(email);
	}

	@Override
	public List<QueuedEmail> getAll() {
		return emailDao.findAll();
	}

	@Override
	public List<QueuedEmail> findByType(String type) {
		return emailDao.findByType(type);
	}

	@Override
	public List<QueuedEmail> findByStatus(int status) {
		return emailDao.findByStatus(status);
	}

}
