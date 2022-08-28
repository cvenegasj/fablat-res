package net.fablat.fablatres.repository;

import net.fablat.fablatres.entities.GroupMember;

import java.util.List;

public interface GroupMemberDAO extends GenericDAO<GroupMember, Integer> {

	Integer countAllByFabberAsCoordinator(String email);

	Integer countAllByFabberAsCollaborator(String email);
	
	GroupMember findByGroupAndFabber(Integer idGroup, String email);
	
	GroupMember findByGroupAndFabber(Integer idGroup, Integer idFabber);
	
	List<GroupMember> findAllByGroup(Integer idGroup);
	
	List<GroupMember> findAllByFabber(String email);
	
	List<GroupMember> findAllByFabber(Integer idFabber);

}
