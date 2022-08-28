package net.fablat.fablatres.repository.impl;

import net.fablat.fablatres.entities.ActivityLog;
import net.fablat.fablatres.repository.ActivityLogDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ActivityLogDAOImpl extends GenericDAOImpl<ActivityLog, Integer> implements ActivityLogDAO {

	@Transactional
	public List<ActivityLog> findAllExternal() {
		List<ActivityLog> list = null;
		list = (List<ActivityLog>) getSession()
				.createQuery(
						"select x from " + getDomainClassName() + " x "
						+ "where x.visibility = 'EXTERNAL' "
						+ "order by x.id desc")
				.list();
		return list;
	}

	@Transactional
	public List<ActivityLog> findAllByGroup(Integer idGroup) {
		List<ActivityLog> list = null;
		list = (List<ActivityLog>) getSession()
				.createQuery(
						"select x from " + getDomainClassName() + " x "
						+ "where (x.visibility = 'INTERNAL' or x.visibility = 'EXTERNAL') "
						+ "and x.level = 'GROUP' "
						+ "and x.group.id = :idGroup "
						+ "order by date(x.creationDateTime) desc")
				.setParameter("idGroup", idGroup)
				.list();
		return list;
	}

	@Transactional
	public List<ActivityLog> findAllBySubGroup(Integer idSubGroup) {
		List<ActivityLog> list = null;
		list = (List<ActivityLog>) getSession()
				.createQuery(
						"select x from " + getDomainClassName() + " x "
						+ "where (x.visibility = 'INTERNAL' or x.visibility = 'EXTERNAL') "
						+ "and x.level = 'SUBGROUP' "
						+ "and x.subGroup.id = :idSubGroup "
						+ "order by date(x.creationDateTime) desc")
				.setParameter("idSubGroup", idSubGroup)
				.list();
		return list;
	}

}
