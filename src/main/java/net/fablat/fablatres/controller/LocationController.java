package net.fablat.fablatres.controller;

import net.fablat.fablatres.dto.LocationDTO;
import net.fablat.fablatres.entities.Location;
import net.fablat.fablatres.repository.LocationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth/locations")
public class LocationController {

	@Autowired
	private LocationDAO locationDAO;
	
	@RequestMapping(value = "/search/{searchText}", method = RequestMethod.GET)
	public List<LocationDTO> findByTerm(@PathVariable("searchText") String searchText) {
		List<LocationDTO> returnList = new ArrayList<LocationDTO>();
		for (Location l : locationDAO.findByTerm(searchText)) {
			LocationDTO lDTO = new LocationDTO();
			lDTO.setIdLocation(l.getIdLocation());
			lDTO.setAddress1(l.getAddress1());
			lDTO.setAddress2(l.getAddress2());
			lDTO.setCity(l.getCity());
			lDTO.setCountry(l.getCountry());
			lDTO.setLatitude(l.getLatitude());
			lDTO.setLongitude(l.getLongitude());
			lDTO.setLabId(l.getLab() != null ? l.getLab().getIdLab() : null);
			lDTO.setLabName(l.getLab() != null ? l.getLab().getName() : null);
			
			String address1 = (lDTO.getAddress1() != null && !lDTO.getAddress1().trim().equals("")) ? lDTO.getAddress1().trim() + ", " : "";
			String city = (lDTO.getCity() != null && !lDTO.getCity().trim().equals("")) ? lDTO.getCity().trim() + ", " : "";
			String country = (lDTO.getCountry() != null && !lDTO.getCountry().trim().equals("")) ? lDTO.getCountry().trim().toUpperCase() : "";
			lDTO.setDisplay(address1 + city + country);
			
			returnList.add(lDTO);
		}
		
		return returnList;
	}
	
}
