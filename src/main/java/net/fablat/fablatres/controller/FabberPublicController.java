package net.fablat.fablatres.controller;

import net.fablat.fablatres.dto.FabberDTO;
import net.fablat.fablatres.entities.Fabber;
import net.fablat.fablatres.entities.FabberInfo;
import net.fablat.fablatres.entities.RoleFabber;
import net.fablat.fablatres.repository.FabberDAO;
import net.fablat.fablatres.repository.RoleDAO;
import net.fablat.fablatres.util.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
public class FabberPublicController {

	@Autowired
	private FabberDAO fabberDAO;

	@Autowired
	private RoleDAO roleDAO;

	@GetMapping("/")
	public Map<String, Object> healthcheck() {
		Map<String, Object> model = Map.of(
				"id", UUID.randomUUID().toString(),
				"content", "Hello World");
		return model;
	}

	@GetMapping("/hello")
	public Map<String, Object> home() {
		Map<String, Object> model = Map.of(
				"id", UUID.randomUUID().toString(),
				"content", "Hello World");
		return model;
	}

	@GetMapping("/public/hello")
	public Map<String, Object> hello() {
		Map<String, Object> model = Map.of(
				"id", UUID.randomUUID().toString(),
				"content", "Hello World");
		return model;
	}

	@RequestMapping(value = "/public/signup", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public FabberDTO createAndFetch(@RequestBody Map parsedJwt) {
		final String email = (String) parsedJwt.get("email");
		final Fabber retrievedUser = fabberDAO.findByEmail(email);

		if (retrievedUser != null) { // if user exists in database, update it
			retrievedUser.setName((String) parsedJwt.get("name")); // map keys depend on Google's JWT
			retrievedUser.setFirstName((String) parsedJwt.get("given_name"));
			retrievedUser.setLastName((String) parsedJwt.get("family_name"));
			retrievedUser.setAvatarUrl((String) parsedJwt.get("picture"));

			Fabber persisted = fabberDAO.makePersistent(retrievedUser);
			return convertToDTO(persisted);
		} else { // create new user
			Fabber newUser = Fabber.builder()
					.email((String) parsedJwt.get("email"))
					.name((String) parsedJwt.get("name"))
					.firstName((String) parsedJwt.get("given_name"))
					.lastName((String) parsedJwt.get("family_name"))
					.avatarUrl((String) parsedJwt.get("picture"))
					.isFabAcademyGrad(false)
					.isNomade(true)
					.enabled(true)
					.build();

			// set RoleFabber: ROLE_USER
			RoleFabber roleFabber = new RoleFabber();
			roleFabber.setRole(roleDAO.findByName(Resources.ROLE_USER));
			roleFabber.setFabber(newUser);
			newUser.setRoleFabbers(Set.of(roleFabber));

			// set FabberInfo
			FabberInfo fabberInfo = new FabberInfo();
			fabberInfo.setFabber(newUser);
			fabberInfo.setScoreGeneral(0);
			fabberInfo.setScoreCoordinator(0);
			fabberInfo.setScoreCollaborator(0);
			fabberInfo.setScoreReplicator(0);
			newUser.setFabberInfo(fabberInfo);

			Fabber persisted = fabberDAO.makePersistent(newUser);
			return convertToDTO(persisted);
		}
	}

	/*@RequestMapping(value = "/public/signup", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public FabberDTO create(@RequestBody FabberDTO fabberDTO) {
		if (fabberDAO.findByEmail(fabberDTO.getEmail()) != null) {
			throw new DuplicateEmailException();
		}

		Fabber fabber = convertToEntity(fabberDTO);
		// encrypt password
		fabber.setPassword(passwordEncoder.encode(fabberDTO.getPassword()));
		fabber.setEnabled(true);

		// fabber's Lab
		if (fabberDTO.getLabId() != null) {
			fabber.setLab(labDAO.findById(fabberDTO.getLabId()));
			fabber.setIsNomade(false);
		} else {
			fabber.setLab(null);
			fabber.setIsNomade(true);
		}

		// assign ROLE_USER
		RoleFabber rf = new RoleFabber();
		rf.setRole(roleDAO.findByName(Resources.ROLE_USER));
		rf.setFabber(fabber);
		fabber.getRoleFabbers().add(rf);
		// fabber's fabberInfo
		FabberInfo info = new FabberInfo();
		info.setFabber(fabber);
		info.setScoreGeneral(0);
		info.setScoreCoordinator(0);
		info.setScoreCollaborator(0);
		info.setScoreReplicator(0);
		fabber.setFabberInfo(info);

		Fabber fabberCreated = fabberDAO.makePersistent(fabber);
		return convertToDTO(fabberCreated);
	}*/


	// ========== DTO conversion ==========

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

	    return fabberDTO;
	}

	private Fabber convertToEntity(FabberDTO fabberDTO) {
		Fabber fabber = new Fabber();
		fabber.setEmail(fabberDTO.getEmail());
		fabber.setName(fabberDTO.getName());
	    fabber.setFirstName(fabberDTO.getFirstName());
	    fabber.setLastName(fabberDTO.getLastName());
	    fabber.setIsFabAcademyGrad(fabberDTO.getIsFabAcademyGrad());
	    fabber.setFabAcademyGradYear(fabberDTO.getIsFabAcademyGrad() ? fabberDTO.getFabAcademyGradYear() : null);

		return fabber;
	}
}
