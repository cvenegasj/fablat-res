package net.fablat.fablatres.repository;

import net.fablat.fablatres.entities.Workshop;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkshopDAO extends GenericDAO<Workshop, Integer> {
	
	List<Workshop> findAllAfterDate(LocalDateTime date);
	
	List<Workshop> findAllBySubGroup(Integer idSubGroup);

}
