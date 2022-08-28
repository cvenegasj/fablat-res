package net.fablat.fablatres.repository.impl;

import net.fablat.fablatres.entities.SubGroupMember;
import net.fablat.fablatres.repository.SubGroupMemberDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class SubGroupMemberDAOImpl extends GenericDAOImpl<SubGroupMember, Integer> implements SubGroupMemberDAO {

	@Transactional
	public Integer countAllByFabberAsCoordinator(String email) {
		Long count = getSession()
				.createQuery(
						"select count(x) from " + getDomainClassName() + " x "
								+ "where x.isCoordinator = 1 and x.groupMember.fabber.email = :email", Long.class)
				.setParameter("email", email)
				.getSingleResult();
		
		return count.intValue();
	}

	@Transactional
	public Integer countAllByFabberAsCollaborator(String email) {
		Long count = getSession()
				.createQuery(
						"select count(x) from " + getDomainClassName() + " x "
								+ "where x.isCoordinator = 0 and x.groupMember.fabber.email = :email", Long.class)
				.setParameter("email", email)
				.getSingleResult();
		
		return count.intValue();
	}
	
	@Transactional
	public SubGroupMember findBySubGroupAndFabber(Integer idSubGroup, String email) {
		SubGroupMember e = null;
		e = (SubGroupMember) getSession()
				.createQuery(
						"from " + getDomainClassName() + " x where x.subGroup.id = :idSubGroup "
								+ "and x.groupMember.fabber.email = :email")
				.setParameter("idSubGroup", idSubGroup)
				.setParameter("email", email).setMaxResults(1).uniqueResult();

		return e;
	}

	@Transactional
	public List<SubGroupMember> findAllBySubGroup(Integer idSubGroup) {
		List<SubGroupMember> list = null;
		list = (List<SubGroupMember>) getSession()
				.createQuery(
						"select x from " + getDomainClassName() + " x "
								+ "where x.subGroup.id = :idSubGroup "
								+ "order by case when x.isCoordinator = 1 then 0 else 1 end, "
								+ "date(x.creationDateTime) asc")
				.setParameter("idSubGroup", idSubGroup)
				.list();

		return list;
	}

	@Transactional
	public List<SubGroupMember> findAllByGroupAndFabber(Integer idGroup, String email) {
		List<SubGroupMember> list = null;
		list = (List<SubGroupMember>) getSession()
				.createQuery(
						"select x from " + getDomainClassName() + " x "
								+ "where x.subGroup.group.id = :idGroup "
								+ "and x.groupMember.fabber.email = :email "
								+ "order by date(x.creationDateTime) asc")
				.setParameter("idGroup", idGroup)
				.setParameter("email", email)
				.list();

		return list;
	}

	@Transactional
	public List<SubGroupMember> findAllByGroupAndFabber(Integer idGroup, Integer idFabber) {
		List<SubGroupMember> list = null;
		list = (List<SubGroupMember>) getSession()
				.createQuery(
						"select x from " + getDomainClassName() + " x "
								+ "where x.subGroup.group.id = :idGroup "
								+ "and x.groupMember.fabber.id = :idFabber "
								+ "order by date(x.creationDateTime) asc")
				.setParameter("idGroup", idGroup)
				.setParameter("idFabber", idFabber)
				.list();

		return list;
	}
	
}
