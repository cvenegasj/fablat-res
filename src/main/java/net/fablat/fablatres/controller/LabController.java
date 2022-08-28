package net.fablat.fablatres.controller;

import net.fablat.fablatres.dto.LabDTO;
import net.fablat.fablatres.dto.LabRequestWrapper;
import net.fablat.fablatres.entities.Lab;
import net.fablat.fablatres.entities.Location;
import net.fablat.fablatres.repository.LabDAO;
import net.fablat.fablatres.repository.LocationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth/labs")
public class LabController {

	@Autowired
	private LabDAO labDAO;
	@Autowired
	private LocationDAO locationDAO;

	// General method for fetching data of all labs
	@RequestMapping(value = "/update-all", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void updateAll(@RequestBody Map<String, List<LabRequestWrapper>> labs) {
		
		for (LabRequestWrapper item : labs.get("labs")) {
			Lab  lab = new Lab();
			lab.setIdLab(item.getId());
			lab.setName(item.getName());
			lab.setDescription(item.getDescription());
			lab.setAvatar(item.getAvatar());
			lab.setPhone(item.getPhone());
			lab.setEmail(item.getEmail());
			lab.setUrl(item.getUrl());

			Location location = new Location();
			location.setAddress1(item.getAddress_1());
			location.setAddress2(item.getAddress_2());
			location.setCity(item.getCity());
			location.setCountry(item.getCountry_code());
			location.setLatitude(item.getLatitude() != null ? item.getLatitude().toString() : null);
			location.setLongitude(item.getLongitude() != null ? item.getLongitude().toString() : null);

			locationDAO.makePersistent(location);
			lab.setLocation(location);
			labDAO.makePersistent(lab);
		}
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public List<LabDTO> findAll() {
		List<LabDTO> returnList = new ArrayList<LabDTO>();

		for (Lab lab : labDAO.findAll()) {
			LabDTO lDTO = new LabDTO();
			lDTO.setIdLab(lab.getIdLab());
			lDTO.setName(lab.getName());
			lDTO.setAddress1(lab.getLocation().getAddress1());
			lDTO.setCity(lab.getLocation().getCity());
			lDTO.setCountry(lab.getLocation().getCountry());
			
			returnList.add(lDTO);
		}
		
		return returnList;
	} 

	@RequestMapping(value = "/search/{searchText}", method = RequestMethod.GET)
	public List<LabDTO> findByTerm(@PathVariable("searchText") String searchText) {
		List<LabDTO> returnList = new ArrayList<LabDTO>();
		for (Lab lab : labDAO.findByTerm(searchText)) {
			LabDTO lDTO = new LabDTO();
			lDTO.setIdLab(lab.getIdLab());
			lDTO.setName(lab.getName());
			lDTO.setAddress1(lab.getLocation().getAddress1());
			lDTO.setCity(lab.getLocation().getCity());
			lDTO.setCountry(lab.getLocation().getCountry());

			returnList.add(lDTO);
		}

		return returnList;
	}
	
}
