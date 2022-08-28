package net.fablat.fablatres.repository.impl;

import net.fablat.fablatres.entities.Lab;
import net.fablat.fablatres.repository.LabDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LabDAOImpl extends GenericDAOImpl<Lab, Integer> implements LabDAO {

	@Transactional
	public List<Lab> findByTerm(String term) {
		List<Lab> list = null;
		list = (List<Lab>) getSession()
				.createQuery("select x from " + getDomainClassName() + " x " + "where lower(x.name) like :term")
				.setParameter("term", "%" + term.toLowerCase() + "%")
				.list();

		return list;
	}
	
}
