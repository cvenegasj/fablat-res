package net.fablat.fablatres.repository.impl;

import net.fablat.fablatres.entities.Workshop;
import net.fablat.fablatres.repository.WorkshopDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class WorkshopDAOImpl extends GenericDAOImpl<Workshop, Integer> implements WorkshopDAO {

	@Transactional
	public List<Workshop> findAllAfterDate(LocalDateTime date) {
		List<Workshop> list = null;
		list = (List<Workshop>) getSession()
				.createQuery(
						"select x from " + getDomainClassName() + " x "
								+ "where x.startDateTime > :date "
								+ "order by date(x.startDateTime) asc")
				.setParameter("date", date)
				.list();
		
		return list;
	}

	@Transactional
	public List<Workshop> findAllBySubGroup(Integer idSubGroup) {
		List<Workshop> list = null;
		list = (List<Workshop>) getSession()
				.createQuery(
						"select x from " + getDomainClassName() + " x "
								+ "where x.subGroup.id = :idSubGroup ")
				.setParameter("idSubGroup", idSubGroup)
				.list();
		
		return list;
	}

}
