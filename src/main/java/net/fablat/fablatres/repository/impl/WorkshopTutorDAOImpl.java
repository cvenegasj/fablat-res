package net.fablat.fablatres.repository.impl;

import net.fablat.fablatres.entities.WorkshopTutor;
import net.fablat.fablatres.repository.WorkshopTutorDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class WorkshopTutorDAOImpl extends GenericDAOImpl<WorkshopTutor, Integer> implements WorkshopTutorDAO {

	@Transactional
	public Integer countAllByFabber(String email) {
		Long count = getSession()
				.createQuery(
						"select count(x) from " + getDomainClassName() + " x "
								+ "where x.subGroupMember.groupMember.fabber.email = :email", Long.class)
				.setParameter("email", email)
				.getSingleResult();
		
		return count.intValue();
	}

	@Transactional
	public WorkshopTutor findByWorkshopAndFabber(Integer idWorkshop, String email) {
		WorkshopTutor e = null;
		e = (WorkshopTutor) getSession()
				.createQuery(
						"from " + getDomainClassName() + " x where x.workshop.id = :idWorkshop "
								+ "and x.subGroupMember.groupMember.fabber.email = :email")
				.setParameter("idWorkshop", idWorkshop)
				.setParameter("email", email).setMaxResults(1).uniqueResult();

		return e;
	}

}
