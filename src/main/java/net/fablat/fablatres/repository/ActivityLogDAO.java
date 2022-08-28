package net.fablat.fablatres.repository;

import net.fablat.fablatres.entities.ActivityLog;

import java.util.List;

public interface ActivityLogDAO extends GenericDAO<ActivityLog, Integer> {

	// External group activities
	List<ActivityLog> findAllExternal();

	// External and Internal group activity
	List<ActivityLog> findAllByGroup(Integer idGroup);

	// External and Internal subgroup activity
	List<ActivityLog> findAllBySubGroup(Integer idSubGroup);

}
