package io.danny.back_end.service;

import io.danny.back_end.domain.Beneficios;
import io.danny.back_end.domain.Clientes;
import io.danny.back_end.exception.ExceptionHandling;
import io.danny.back_end.model.BeneficiosDTO;
import io.danny.back_end.model.ClientesDTO;
import io.danny.back_end.model.response.ClientesResponseDTO;
import io.danny.back_end.repos.BeneficiosRepository;
import io.danny.back_end.repos.ClientesRepository;
import io.danny.back_end.utils.EcReturn;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ClientesService {

    private final ClientesRepository clientesRepository;
    private final BeneficiosRepository beneficiosRepository;

    public ClientesService(final ClientesRepository clientesRepository,
            final BeneficiosRepository beneficiosRepository) {
        this.clientesRepository = clientesRepository;
        this.beneficiosRepository = beneficiosRepository;
    }

    public EcReturn findAll() {
    	
    	EcReturn vl_return = new EcReturn();
    	
    	try {
    		   List<ClientesResponseDTO> clientesResponseDTOs=clientesRepository.findAll(Sort.by("idCli"))
    	                .stream()
    	                .map(clientes -> responseMapToDTO(clientes, new ClientesResponseDTO()))
    	                .collect(Collectors.toList());
    		   vl_return.setCorrectProcess(true);
    		   vl_return.setData(clientesResponseDTOs);
    		   return vl_return;
		} catch (Exception err) {
			 return ExceptionHandling.get(err);
		}
   
    }

    public ClientesDTO get(final Long idCli) {
        return clientesRepository.findById(idCli)
                .map(clientes -> mapToDTO(clientes, new ClientesDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ClientesDTO clientesDTO) {
        final Clientes clientes = new Clientes();
        mapToEntity(clientesDTO, clientes);
        return clientesRepository.save(clientes).getIdCli();
    }

    public void update(final Long idCli, final ClientesDTO clientesDTO) {
        final Clientes clientes = clientesRepository.findById(idCli)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(clientesDTO, clientes);
        clientesRepository.save(clientes);
    }

    public void delete(final Long idCli) {
        clientesRepository.deleteById(idCli);
    }
    
 public EcReturn saveClient(ClientesDTO clientesDTO) {
    	
    	try {
    		
    		EcReturn vl_return = new EcReturn();
    		vl_return.setCorrectProcess(true);
    		
    		// GET BENEFICIOS
    		
    		Long estado=(long) 0;
    		
    		List<BeneficiosDTO> Listbeneficios= beneficiosRepository.findByEstadoBenAndTipoBen(estado,clientesDTO.getGrupoCli())
    	                .stream()
    	                .map(beneficios -> BeneficiosService.mapToDTO(beneficios, new BeneficiosDTO()))
    	                .collect(Collectors.toList());
    	
    		
    		
    		// VERIFICO SI EXISTEN BENEFICIOS DISPONIBLES
    		
    		if(Listbeneficios !=null && Listbeneficios.size()>0 ) {
    			
    			if(clientesRepository.existsByNombreCliAndGrupoCliIgnoreCase(clientesDTO.getNombreCli(),clientesDTO.getGrupoCli())) {
    				vl_return.setCorrectProcess(false);
           	     vl_return.setMessage("El nombre ya se encuentra registrado en el grupo");
           	
    			}else {
    				
    				//GUARDO EL CLIENTE
    				
    				final Clientes clientes = new Clientes();
            		clientesDTO.setIdBen(Listbeneficios.get(0).getIdBen());
                    mapToEntity(clientesDTO, clientes);
                    clientesRepository.save(clientes).getIdCli();
                    clientesDTO.setIdCli(clientes.getIdCli());
                     
                     
                    ClientesResponseDTO clientesRespose=new ClientesResponseDTO();
                    mapResponseToDTO(clientesDTO,clientesRespose);
                    
            		
            	     vl_return.setCorrectProcess(true);
            	     vl_return.setMessage("Datos guardados");
            		 vl_return.setData(clientesRespose);
            		 
            		 
            		 // ACTUALIZO EL ESTADO DEL BENEFICIO
            		 
            		 Listbeneficios.get(0).setEstadoBen(new Long(1));
            		 final Beneficios beneficios = beneficiosRepository.findById(Listbeneficios.get(0).getIdBen())
            	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            		 BeneficiosService.mapToEntity(Listbeneficios.get(0), beneficios);
            	     beneficiosRepository.save(beneficios);
    			}
    			
    		}else {
    			
    			 vl_return.setCorrectProcess(false);
        	     vl_return.setMessage("No hay mas beneficios disponibles");
    		}
    		
    		return vl_return;
			
		} catch (Exception err) {
			 return ExceptionHandling.get(err);
		}
    }

    private ClientesDTO mapToDTO(final Clientes clientes, final ClientesDTO clientesDTO) {
        clientesDTO.setIdCli(clientes.getIdCli());
        clientesDTO.setNombreCli(clientes.getNombreCli());
        clientesDTO.setCorreoCli(clientes.getCorreoCli());
        clientesDTO.setNumeroCli(clientes.getNumeroCli());
        clientesDTO.setGrupoCli(clientes.getGrupoCli());
        clientesDTO.setIdBen(clientes.getIdBen() == null ? null : clientes.getIdBen().getIdBen());
        return clientesDTO;
    }
    
    private ClientesResponseDTO responseMapToDTO(final Clientes clientes, final ClientesResponseDTO clientesResponseDTO) {
    	clientesResponseDTO.setIdCli(clientes.getIdCli());
    	clientesResponseDTO.setNombreCli(clientes.getNombreCli());
    	clientesResponseDTO.setCorreoCli(clientes.getCorreoCli());
    	clientesResponseDTO.setNumeroCli(clientes.getNumeroCli());
    	clientesResponseDTO.setGrupoCli(clientes.getGrupoCli());
    	
    	 final Beneficios idBen = clientes.getIdBen() == null ? null : beneficiosRepository.findById(clientes.getIdBen().getIdBen())
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "idBen not found"));
    	
    	clientesResponseDTO.setNombreBen(idBen.getNombreBen());
        return clientesResponseDTO;
    }

    private Clientes mapToEntity(final ClientesDTO clientesDTO, final Clientes clientes) {
        clientes.setNombreCli(clientesDTO.getNombreCli());
        clientes.setCorreoCli(clientesDTO.getCorreoCli());
        clientes.setNumeroCli(clientesDTO.getNumeroCli());
        clientes.setGrupoCli(clientesDTO.getGrupoCli());
        final Beneficios idBen = clientesDTO.getIdBen() == null ? null : beneficiosRepository.findById(clientesDTO.getIdBen())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "idBen not found"));
        clientes.setIdBen(idBen);
        return clientes;
    }
    
    private ClientesResponseDTO mapResponseToDTO(final ClientesDTO clientes, final ClientesResponseDTO clientesResponseDTO) {
    	clientesResponseDTO.setIdCli(clientes.getIdCli());
    	clientesResponseDTO.setNombreCli(clientes.getNombreCli());
    	clientesResponseDTO.setCorreoCli(clientes.getCorreoCli());
    	clientesResponseDTO.setNumeroCli(clientes.getNumeroCli());
    	clientesResponseDTO.setGrupoCli(clientes.getGrupoCli());
    	 final Beneficios idBen = clientes.getIdBen() == null ? null : beneficiosRepository.findById(clientes.getIdBen())
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "idBen not found"));
    	 clientesResponseDTO.setNombreBen(idBen.getNombreBen());
         
        return clientesResponseDTO;
    }
    
    
    


}
