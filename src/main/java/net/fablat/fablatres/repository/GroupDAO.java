package net.fablat.fablatres.repository;

import net.fablat.fablatres.entities.Group;

import java.util.List;

public interface GroupDAO extends GenericDAO<Group, Integer> {

	List<Group> findByTerm(String term);
	
	List<Group> findAllOrderDate();
	
	Integer getMembersCount(Integer idGroup);
	
	Integer getSubGroupsCount(Integer idGroup);
	
	Boolean checkIfMember(Integer idGroup, String email);
	
	Boolean checkIfCoordinator(Integer idGroup, String email);

}
