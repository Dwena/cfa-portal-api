package fr.dawan.AppliCFABack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.dto.CentreFormationDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.services.CentreFormationService;

@RestController
@RequestMapping("/centreFormations")
public class CentreFormationController {

	@Autowired
	CentreFormationService centreFormationService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<CentreFormationDto> getAll() {
		return centreFormationService.getAllCentreFormation();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public CentreFormationDto getById(@PathVariable("id") long id) {
		return centreFormationService.getById(id);
	}

	// /AppliCFABack/groupeEtudiants/{page}/{size}
	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<CentreFormationDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return centreFormationService.getAllCentreFormation(page, size);
	}
	
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<CentreFormationDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return centreFormationService.getAllCentreFormations(page, size, search.get());
 		else
 			return centreFormationService.getAllCentreFormations(page, size, "");
 	}
	
	
	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
		return centreFormationService.count("");
	}
	
	@GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
		if(search.isPresent())
			return centreFormationService.count(search.get());
		else
			return centreFormationService.count("");
	}
	

	// ##################################################
	// # POST #
	// ##################################################

	@PostMapping(consumes = "application/json", produces = "application/json")
	public CentreFormationDto save(@RequestBody CentreFormationDto cfDto) {
		return centreFormationService.saveOrUpdate(cfDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			centreFormationService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectu??e");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non r??alis??e");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public CentreFormationDto update(@RequestBody CentreFormationDto cfDto) {
		return centreFormationService.saveOrUpdate(cfDto);
	}

}
