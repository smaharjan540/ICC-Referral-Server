package icc.referral.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import icc.referral.model.Role;

public interface RoleDao extends JpaRepository<Role, Long> {

	Role findByType(String type);

}
