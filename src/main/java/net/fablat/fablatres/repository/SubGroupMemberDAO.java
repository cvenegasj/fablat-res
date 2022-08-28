package net.fablat.fablatres.repository;

import net.fablat.fablatres.entities.SubGroupMember;

import java.util.List;

public interface SubGroupMemberDAO extends GenericDAO<SubGroupMember, Integer> {
	
	Integer countAllByFabberAsCoordinator(String email);

	Integer countAllByFabberAsCollaborator(String email);

	SubGroupMember findBySubGroupAndFabber(Integer idSubGroup, String email);
	
	List<SubGroupMember> findAllBySubGroup(Integer idSubGroup);
	
	List<SubGroupMember> findAllByGroupAndFabber(Integer idGroup, String email);
	
	List<SubGroupMember> findAllByGroupAndFabber(Integer idGroup, Integer idFabber);
	
}
