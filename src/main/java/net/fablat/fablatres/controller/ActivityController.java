package net.fablat.fablatres.controller;

import net.fablat.fablatres.dto.ActivityLogDTO;
import net.fablat.fablatres.entities.ActivityLog;
import net.fablat.fablatres.repository.ActivityLogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/auth/activities")
public class ActivityController {
	
	@Autowired
	private ActivityLogDAO activityLogDAO;

	@RequestMapping(value = "/find-all-external", method = RequestMethod.GET)
	public List<ActivityLogDTO> findAllExternal() {
		// Single list with all types of activities
		List<ActivityLogDTO> returnList = new ArrayList<ActivityLogDTO>();
		
		for (ActivityLog a : activityLogDAO.findAllExternal()) {
			ActivityLogDTO aDTO = convertToDTO(a);
			returnList.add(aDTO);
		}
		return returnList;
	}
	
	@RequestMapping(value = "/group/{idGroup}", method = RequestMethod.GET)
	public List<ActivityLogDTO> findAllFromGroup(@PathVariable("idGroup") Integer idGroup) {
		List<ActivityLogDTO> returnList = new ArrayList<ActivityLogDTO>();
		
		for (ActivityLog a : activityLogDAO.findAllByGroup(idGroup)) {
			ActivityLogDTO aDTO = convertToDTO(a);
			returnList.add(aDTO);
		}
		return returnList;
	}
	
	@RequestMapping(value = "/subgroup/{idSubGroup}", method = RequestMethod.GET)
	public List<ActivityLogDTO> findAllFromSubGroup(@PathVariable("idSubGroup") Integer idSubGroup) {
		List<ActivityLogDTO> returnList = new ArrayList<ActivityLogDTO>();
		
		for (ActivityLog a : activityLogDAO.findAllBySubGroup(idSubGroup)) {
			ActivityLogDTO aDTO = convertToDTO(a);
			returnList.add(aDTO);
		}
		return returnList;
	}
	
	private ActivityLogDTO convertToDTO(ActivityLog a) {
		ActivityLogDTO aDTO = new ActivityLogDTO();
		aDTO.setIdActivityLog(a.getIdActivityLog());
		aDTO.setLevel(a.getLevel());
		aDTO.setType(a.getType());
		aDTO.setVisibility(a.getVisibility());
		
		aDTO.setCreationDateTime(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(a.getCreationDateTime()));
		aDTO.setCreationDateTimeRaw(a.getCreationDateTime());
		aDTO.setGroupId(a.getGroup() != null ? a.getGroup().getIdGroup() : null);
		aDTO.setGroupName(a.getGroup() != null ? a.getGroup().getName() : null);
		aDTO.setSubGroupId(a.getSubGroup() != null ? a.getSubGroup().getIdSubGroup() : null);
		aDTO.setSubGroupName(a.getSubGroup() != null ? a.getSubGroup().getName() : null);
		aDTO.setFabberId(a.getFabber().getIdFabber());
		aDTO.setFabberFirstName(a.getFabber().getFirstName());
		aDTO.setFabberLastName(a.getFabber().getLastName());
		return aDTO;
	}
	
}
