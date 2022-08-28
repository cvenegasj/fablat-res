package net.fablat.fablatres.repository;

import net.fablat.fablatres.entities.WorkshopTutor;

public interface WorkshopTutorDAO extends GenericDAO<WorkshopTutor, Integer> {

	Integer countAllByFabber(String email);

	WorkshopTutor findByWorkshopAndFabber(Integer idWorkshop, String email);

}
