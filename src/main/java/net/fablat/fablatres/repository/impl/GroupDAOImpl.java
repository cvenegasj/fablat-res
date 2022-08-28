package net.fablat.fablatres.repository.impl;

import net.fablat.fablatres.entities.Group;
import net.fablat.fablatres.entities.GroupMember;
import net.fablat.fablatres.repository.GroupDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class GroupDAOImpl extends GenericDAOImpl<Group, Integer> implements GroupDAO {

	@Transactional
	public List<Group> findByTerm(String term) {
		List<Group> list = null;
		list = (List<Group>) getSession()
				.createQuery(
						"select x from " + getDomainClassName() + " x "
								+ "where lower(x.name) like :term")
				.setParameter("term", "%" + term.toLowerCase() + "%")
				.list();
		
		return list;
	}

	@Transactional
	public List<Group> findAllOrderDate() {
		List<Group> list = null;
		list = (List<Group>) getSession()
				.createQuery(
						"select x from " + getDomainClassName() + " x "
								+ "order by x.creationDateTime asc")
				.list();
		
		return list;
	}

	@Transactional
	public Integer getMembersCount(Integer idGroup) {
		Long count = getSession()
				.createQuery(
						"select count(distinct x) from GroupMember x "
								+ "where x.group.id = :idGroup", Long.class)
				.setParameter("idGroup", idGroup)
				.getSingleResult();
		
		return count.intValue();
	}

	@Transactional
	public Integer getSubGroupsCount(Integer idGroup) {
		Long count = getSession()
				.createQuery(
						"select count(distinct x) from SubGroup x "
								+ "where x.group.id = :idGroup", Long.class)
				.setParameter("idGroup", idGroup)
				.getSingleResult();
		
		return count.intValue();
	}

	@Transactional
	public Boolean checkIfMember(Integer idGroup, String email) {
		GroupMember e = null;
		e = (GroupMember) getSession()
				.createQuery(
						"select x from GroupMember x "
								+ "where x.group.id = :idGroup and x.fabber.email = :email")
				.setParameter("idGroup", idGroup)
				.setParameter("email", email)
				.setMaxResults(1).uniqueResult();
		
		return e != null;
	}

	@Transactional
	public Boolean checkIfCoordinator(Integer idGroup, String email) {
		GroupMember e = null;
		e = (GroupMember) getSession()
				.createQuery(
						"select x from GroupMember x "
								+ "where x.group.id = :idGroup and x.fabber.email = :email")
				.setParameter("idGroup", idGroup)
				.setParameter("email", email)
				.setMaxResults(1).uniqueResult();
		
		return e != null ? e.getIsCoordinator() : false;
	}

}
