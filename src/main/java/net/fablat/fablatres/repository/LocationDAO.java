package net.fablat.fablatres.repository;

import net.fablat.fablatres.entities.Location;

import java.util.List;

public interface LocationDAO extends GenericDAO<Location, Integer> {

	List<Location> findByTerm(String term);

}
