package net.fablat.fablatres.controller;

import net.fablat.fablatres.dto.FabberDTO;
import net.fablat.fablatres.entities.Fabber;
import net.fablat.fablatres.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/auth/fabbers")
public class FabberController {

	@Autowired
	private FabberDAO fabberDAO;
	@Autowired
	private LabDAO labDAO;
	@Autowired
	private SubGroupDAO subGroupDAO;
	@Autowired
	private GroupMemberDAO groupMemberDAO;
	@Autowired
	private SubGroupMemberDAO subGroupMemberDAO;
	@Autowired
	private WorkshopTutorDAO workshopTutorDAO;
	@Autowired
	private RoleDAO roleDAO;
	
	@RequestMapping(value = "/me/general/{email}", method = RequestMethod.GET)
	public FabberDTO getMyGeneralInfo(@PathVariable String email) {
		// update the scores info 
		calculateAndUpdateScores(email);
		// user logged in with email as username
		FabberDTO fabberDTO = convertToDTO(fabberDAO.findByEmail(email));
		return fabberDTO;
	}
	
	private void calculateAndUpdateScores(String email) {
		Integer replicatorScore = workshopTutorDAO.countAllByFabber(email);
		Integer collaboratorScore = groupMemberDAO.countAllByFabberAsCollaborator(email) 
										+ subGroupMemberDAO.countAllByFabberAsCollaborator(email);
		Integer coordinatorScore = groupMemberDAO.countAllByFabberAsCoordinator(email)
										+ subGroupMemberDAO.countAllByFabberAsCoordinator(email);
		Integer generalScore = replicatorScore + collaboratorScore + coordinatorScore;
		
		Fabber fabber = fabberDAO.findByEmail(email);
		fabber.getFabberInfo().setScoreGeneral(generalScore);
		fabber.getFabberInfo().setScoreCoordinator(coordinatorScore);
		fabber.getFabberInfo().setScoreCollaborator(collaboratorScore);
		fabber.getFabberInfo().setScoreReplicator(replicatorScore);
		
		fabberDAO.makePersistent(fabber);
	}
		
	@RequestMapping(value = "/me/profile/{email}", method = RequestMethod.GET)
	public FabberDTO getMyProfile(@PathVariable String email) {
		Fabber fabber = fabberDAO.findByEmail(email);
		FabberDTO fabberDTO = convertToDTO(fabber);
		// Populate authorities
		fabberDTO.setAuthorities(
				fabber.getRoleFabbers().stream()
					.map(roleFabber -> roleFabber.getRole().getName())
					.collect(Collectors.toList())
		);

		return fabberDTO;
	}
	
	@RequestMapping(value = "/{idFabber}", method = RequestMethod.GET)
    public FabberDTO findOne(@PathVariable("idFabber") Integer idFabber) {
        return convertToDTO(fabberDAO.findById(idFabber));
    }
	
	@RequestMapping(value = "/me/update/{email}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateMe(@PathVariable String email, @RequestBody FabberDTO fabberDTO) {
        Fabber fabber = fabberDAO.findByEmail(email);
        fabber.setFirstName(fabberDTO.getFirstName());
        fabber.setLastName(fabberDTO.getLastName());
        fabber.setIsFabAcademyGrad(fabberDTO.getIsFabAcademyGrad());
        fabber.setFabAcademyGradYear(fabberDTO.getIsFabAcademyGrad() ? fabberDTO.getFabAcademyGradYear() : null);
        fabber.setCity(fabberDTO.getCity());
        fabber.setCountry(fabberDTO.getCountry());
        fabber.setMainQuote(fabberDTO.getMainQuote());
        fabber.setWeekGoal(fabberDTO.getWeekGoal());
        		
		// lab
		if (fabberDTO.getLabId() != null) {
			fabber.setLab(labDAO.findById(fabberDTO.getLabId()));
			fabber.setIsNomade(false);
		} else {
			fabber.setLab(null);
			fabber.setIsNomade(true);
		}
        fabberDAO.makePersistent(fabber);
    }
	
	/*@RequestMapping(value = "/me/update-password", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@RequestBody Map<String, String> values, Principal principal) {
		Fabber me = fabberDAO.findByEmail(principal.getName());
		if (!passwordEncoder.matches(values.get("password"), me.getPassword())) {
			throw new InvalidPasswordException();
		}
		
		me.setPassword(passwordEncoder.encode(values.get("newPassword")));
		fabberDAO.makePersistent(me);
	}*/
	
	
	// ========== DTO conversion ==========
	// guide: http://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
	
	private FabberDTO convertToDTO(Fabber fabber) {		
		FabberDTO fabberDTO = new FabberDTO();
		fabberDTO.setIdFabber(fabber.getIdFabber());
		fabberDTO.setEmail(fabber.getEmail());
		fabberDTO.setName(fabber.getName());
		fabberDTO.setFirstName(fabber.getFirstName());
		fabberDTO.setLastName(fabber.getLastName());
		fabberDTO.setIsFabAcademyGrad(fabber.getIsFabAcademyGrad());
		fabberDTO.setFabAcademyGradYear(fabber.getFabAcademyGradYear());
		fabberDTO.setCellPhoneNumber(fabber.getCellPhoneNumber());
		fabberDTO.setIsNomade(fabber.getIsNomade());
		fabberDTO.setMainQuote(fabber.getMainQuote());
		fabberDTO.setCity(fabber.getCity());
		fabberDTO.setCountry(fabber.getCountry());
		fabberDTO.setWeekGoal(fabber.getWeekGoal());
		fabberDTO.setAvatarUrl(fabber.getAvatarUrl());
		fabberDTO.setLabId(fabber.getLab() != null ? fabber.getLab().getIdLab() : null);
		fabberDTO.setLabName(fabber.getLab() != null ? fabber.getLab().getName() : null);
		fabberDTO.setGeneralScore(fabber.getFabberInfo().getScoreGeneral());
		fabberDTO.setCoordinatorScore(fabber.getFabberInfo().getScoreCoordinator());
		fabberDTO.setCollaboratorScore(fabber.getFabberInfo().getScoreCollaborator());
		fabberDTO.setReplicatorScore(fabber.getFabberInfo().getScoreReplicator());
				
	    return fabberDTO;
	}

}
