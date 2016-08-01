package icc.referral.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import icc.referral.model.Faculty;

public interface FacultyDao extends JpaRepository<Faculty, Long> {
	
}
