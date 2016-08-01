package icc.referral.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import icc.referral.model.QueuedEmail;

public interface QueuedEmailDao extends JpaRepository<QueuedEmail, Long> {

	List<QueuedEmail> findByType(String type);

	List<QueuedEmail> findByStatus(int status);

}
