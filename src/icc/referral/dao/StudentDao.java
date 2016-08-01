package icc.referral.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import icc.referral.model.Course;
import icc.referral.model.Student;

public interface StudentDao extends JpaRepository<Student, Integer> {
	
	Student findByUsername(String username);

}
