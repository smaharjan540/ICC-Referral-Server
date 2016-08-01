package icc.referral.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import icc.referral.dao.RoleDao;
import icc.referral.model.Role;

@Service("roleService")
@Transactional(propagation = Propagation.REQUIRED)
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;

	@Override
	public Role addRole(Role role) {
		return roleDao.save(role);
	}

	@Override
	public List<Role> getRoles() {
		return roleDao.findAll();
	}

	@Override
	public void delete(long id) {
		roleDao.delete(id);

	}

}
