package icc.referral.service;

import java.util.List;

import icc.referral.model.Role;

public interface RoleService {

	Role addRole(Role role);

	List<Role> getRoles();

	void delete(long id);
	
}
