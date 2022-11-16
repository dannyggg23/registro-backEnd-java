package io.danny.back_end.rest;

import io.danny.back_end.model.BeneficiosDTO;
import io.danny.back_end.model.ThFormatoDTO;
import io.danny.back_end.model.SkFormatoDTO;
import io.danny.back_end.service.BeneficiosService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.danny.back_end.utils.EcReturn;


@RestController
@RequestMapping(value = "/api/beneficios", produces = MediaType.APPLICATION_JSON_VALUE)
public class BeneficiosResource {

    private final BeneficiosService beneficiosService;

    public BeneficiosResource(final BeneficiosService beneficiosService) {
        this.beneficiosService = beneficiosService;
    }

    @GetMapping
    public ResponseEntity<List<BeneficiosDTO>> getAllBeneficioss() {
        return ResponseEntity.ok(beneficiosService.findAll());
    }

    @GetMapping("/{idBen}")
    public ResponseEntity<BeneficiosDTO> getBeneficios(@PathVariable final Long idBen) {
        return ResponseEntity.ok(beneficiosService.get(idBen));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createBeneficios(
            @RequestBody @Valid final BeneficiosDTO beneficiosDTO) {
        return new ResponseEntity<>(beneficiosService.create(beneficiosDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{idBen}")
    public ResponseEntity<Void> updateBeneficios(@PathVariable final Long idBen,
            @RequestBody @Valid final BeneficiosDTO beneficiosDTO) {
        beneficiosService.update(idBen, beneficiosDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idBen}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteBeneficios(@PathVariable final Long idBen) {
        beneficiosService.delete(idBen);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("uploadSk")
    
  	public EcReturn uploadSk(@RequestBody @Valid final SkFormatoDTO skFormato){
    	return beneficiosService.uploadSk(skFormato);
    }
    
	@PostMapping(path =  "uploadTh")
	  	public EcReturn uploadSh(@RequestBody ThFormatoDTO shFormatoDTO){
	
	    return beneficiosService.uploadSh(shFormatoDTO);
	}

}
