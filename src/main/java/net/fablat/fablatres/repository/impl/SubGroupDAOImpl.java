package net.fablat.fablatres.repository.impl;

import net.fablat.fablatres.entities.SubGroup;
import net.fablat.fablatres.repository.SubGroupDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class SubGroupDAOImpl extends GenericDAOImpl<SubGroup, Integer> implements SubGroupDAO {

	@Transactional
	public List<SubGroup> findAllOrderedAsc() {
		List<SubGroup> list = null;
		
		list = (List<SubGroup>) getSession()
				.createQuery("from " + getDomainClassName() + " x " 
						+ "order by x.name asc")
				.list();

		return list;
	}

	@Transactional
	public List<SubGroup> findAllByGroup(Integer idGroup) {
		List<SubGroup> list = null;
		
		list = (List<SubGroup>) getSession()
				.createQuery("from " + getDomainClassName() + " x "
						+ "where x.group.id = :idGroup "
						+ "order by x.name asc")
				.setParameter("idGroup", idGroup)
				.list();

		return list;
	}

	@Transactional
	public Integer getMembersCount(Integer idSubGroup) {
		Long count = getSession()
				.createQuery(
						"select count(distinct x) from SubGroupMember x "
								+ "where x.subGroup.id = :idSubGroup", Long.class)
				.setParameter("idSubGroup", idSubGroup)
				.getSingleResult();
		
		return count.intValue();
	}

	@Transactional
	public Integer getWorkshopsCount(Integer idSubGroup) {
		Long count = getSession()
				.createQuery(
						"select count(distinct x) from Workshop x "
								+ "where x.subGroup.id = :idSubGroup", Long.class)
				.setParameter("idSubGroup", idSubGroup)
				.getSingleResult();
		
		return count.intValue();
	}
	
}
