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

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.FicheEntrepriseDto;
import fr.dawan.AppliCFABack.services.FicheEntrepriseService;

@RestController
@RequestMapping("/ficheEntreprises")
public class FicheEntrepriseController {

	@Autowired
	FicheEntrepriseService ficheEntrepriseService;

	// ##################################################
	// # GET #
	// ##################################################

	@GetMapping(produces = "application/json")
	public List<FicheEntrepriseDto> getAll() {
		return ficheEntrepriseService.getAllFicheEntreprise();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public FicheEntrepriseDto getById(@PathVariable("id") long id) {
		return ficheEntrepriseService.getById(id);
	}

	@GetMapping(value = "/{page}/{size}", produces = "application/json")
	public @ResponseBody List<FicheEntrepriseDto> getAllByPage(@PathVariable("page") int page,
			@PathVariable(value = "size") int size) {
		return ficheEntrepriseService.getAllByPage(page, size, "");
	}
	
	@GetMapping(value = "/{page}/{size}/{search}", produces = "application/json")
 	public @ResponseBody List<FicheEntrepriseDto> getAllByPage(@PathVariable("page") int page,
 			@PathVariable(value = "size") int size, @PathVariable(value = "search", required = false) Optional<String> search) {
 		if(search.isPresent())
 			return ficheEntrepriseService.getAllByPage(page, size, search.get());
 		else
 			return ficheEntrepriseService.getAllByPage(page, size, "");
 	}

		
	@GetMapping(value = "/count", produces = "application/json")
	public CountDto count() {
//		return ficheEntrepriseService.count("");
		return null;
	}
    
    @GetMapping(value = "/count/{search}", produces = "application/json")
	public CountDto count(@PathVariable(value = "search", required = false) Optional<String> search) {
//		if(search.isPresent())
//			return ficheEntrepriseService.count(search.get());
//		else
//			return ficheEntrepriseService.count("");
    	return null;
	}
    
    @GetMapping(value = "/etudiant/{id}", produces = "application/json")
	public FicheEntrepriseDto getByIdEtudiant(@PathVariable("id") long id) {
		return ficheEntrepriseService.getByIdEtudiant(id);
	}
    


	// ##################################################
	// # POST #
	// ##################################################

	@PostMapping(consumes = "application/json", produces = "application/json")
	public FicheEntrepriseDto save(@RequestBody FicheEntrepriseDto fDto) {
		return ficheEntrepriseService.saveOrUpdate(fDto);
	}

	// ##################################################
	// # DELETE #
	// ##################################################

	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
		try {
			ficheEntrepriseService.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("suppression effectu??e");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non r??alis??e");
		}

	}

	// ##################################################
	// # PUT #
	// ##################################################

	@PutMapping(consumes = "application/json", produces = "application/json")
	public FicheEntrepriseDto update(@RequestBody FicheEntrepriseDto fDto) {
		return ficheEntrepriseService.saveOrUpdate(fDto);
	}
}
