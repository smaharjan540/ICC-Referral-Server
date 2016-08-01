package icc.referral.service;

import java.util.List;

import icc.referral.model.QueuedEmail;

public interface QueuedEmailService {

	QueuedEmail addEmail(QueuedEmail email);

	List<QueuedEmail> getAll();

	List<QueuedEmail> findByType(String type);

	List<QueuedEmail> findByStatus(int status);

}
