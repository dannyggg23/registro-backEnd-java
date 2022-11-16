package io.danny.back_end.rest;

import io.danny.back_end.model.ClientesDTO;
import io.danny.back_end.model.SkFormatoDTO;
import io.danny.back_end.model.response.ClientesResponseDTO;
import io.danny.back_end.service.ClientesService;
import io.danny.back_end.utils.EcReturn;
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


@RestController
@RequestMapping(value = "/api/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientesResource {

    private final ClientesService clientesService;

    public ClientesResource(final ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    public EcReturn getAllClientess() {
    	
        return clientesService.findAll();
        
        
    }

    @GetMapping("/{idCli}")
    public ResponseEntity<ClientesDTO> getClientes(@PathVariable final Long idCli) {
        return ResponseEntity.ok(clientesService.get(idCli));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createClientes(@RequestBody @Valid final ClientesDTO clientesDTO) {
        return new ResponseEntity<>(clientesService.create(clientesDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{idCli}")
    public ResponseEntity<Void> updateClientes(@PathVariable final Long idCli,
            @RequestBody @Valid final ClientesDTO clientesDTO) {
        clientesService.update(idCli, clientesDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idCli}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteClientes(@PathVariable final Long idCli) {
        clientesService.delete(idCli);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("saveClient")
  	public EcReturn uploadSk(@RequestBody @Valid final ClientesDTO clientesDTO){
    	return clientesService.saveClient(clientesDTO);
    }
  
    
}
