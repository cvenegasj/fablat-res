package net.fablat.fablatres.repository.impl;

import net.fablat.fablatres.entities.Role;
import net.fablat.fablatres.repository.RoleDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RoleDAOImpl extends GenericDAOImpl<Role, Integer> implements RoleDAO {

	@Transactional
	public Role findByName(String name) {
		Role role = null;
		role = (Role) getSession().createQuery("from " + getDomainClassName() + " x where x.name = :name")
				.setParameter("name", name)
				.setMaxResults(1).uniqueResult();

		return role;
	}

}
