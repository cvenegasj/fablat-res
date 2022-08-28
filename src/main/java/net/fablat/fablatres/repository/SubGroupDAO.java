package net.fablat.fablatres.repository;

import net.fablat.fablatres.entities.SubGroup;

import java.util.List;

public interface SubGroupDAO extends GenericDAO<SubGroup, Integer> {

	List<SubGroup> findAllOrderedAsc();
	
	List<SubGroup> findAllByGroup(Integer idGroup);
	
	Integer getMembersCount(Integer idSubGroup);
	
	Integer getWorkshopsCount(Integer idSubGroup);

}
