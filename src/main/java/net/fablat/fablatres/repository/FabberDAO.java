package net.fablat.fablatres.repository;

import net.fablat.fablatres.entities.Fabber;

import java.util.List;

public interface FabberDAO extends GenericDAO<Fabber, Integer> {

	Fabber findByEmail(String email);

	List<Fabber> findByTerm(String term);

	Integer countAll();
	
}
