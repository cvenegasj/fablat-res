package net.fablat.fablatres.repository;

import net.fablat.fablatres.entities.Lab;

import java.util.List;

public interface LabDAO extends GenericDAO<Lab, Integer> {

	List<Lab> findByTerm(String term);
}
