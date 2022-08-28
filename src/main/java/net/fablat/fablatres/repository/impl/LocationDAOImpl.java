package net.fablat.fablatres.repository.impl;

import net.fablat.fablatres.entities.Location;
import net.fablat.fablatres.repository.LocationDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LocationDAOImpl extends GenericDAOImpl<Location, Integer> implements LocationDAO {

	@Transactional
	public List<Location> findByTerm(String term) {
		List<Location> list = null;
		list = (List<Location>) getSession()
				.createQuery("select x from " + getDomainClassName() + " x " 
						+ "left join x.lab l "
						+ "where lower(x.address1) like :term or "
						+ "lower(x.address2) like :term or "
						+ "lower(l.name) like :term")
				.setParameter("term", "%" + term.toLowerCase() + "%")
				.list();
		
		return list;
	}

}
