package io.danny.back_end.service;

import io.danny.back_end.domain.Beneficios;
import io.danny.back_end.exception.ExceptionHandling;
import io.danny.back_end.model.BeneficiosDTO;
import io.danny.back_end.model.ThFormatoDTO;
import io.danny.back_end.model.SkFormatoDTO;
import io.danny.back_end.pojos.SkFormato;
import io.danny.back_end.repos.BeneficiosRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import io.danny.back_end.utils.EcReturn;
import io.danny.back_end.utils.GetPropertyValues;
import io.github.classgraph.Resource;


@Service
public class BeneficiosService {
	
	InputStream inputStream;

    private final BeneficiosRepository beneficiosRepository;

    public BeneficiosService(final BeneficiosRepository beneficiosRepository) {
        this.beneficiosRepository = beneficiosRepository;
    }

    public List<BeneficiosDTO> findAll() {
        return beneficiosRepository.findAll(Sort.by("idBen"))
                .stream()
                .map(beneficios -> mapToDTO(beneficios, new BeneficiosDTO()))
                .collect(Collectors.toList());
    }

    public BeneficiosDTO get(final Long idBen) {
        return beneficiosRepository.findById(idBen)
                .map(beneficios -> mapToDTO(beneficios, new BeneficiosDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final BeneficiosDTO beneficiosDTO) {
        final Beneficios beneficios = new Beneficios();
        mapToEntity(beneficiosDTO, beneficios);
        return beneficiosRepository.save(beneficios).getIdBen();
    }

    public void update(final Long idBen, final BeneficiosDTO beneficiosDTO) {
        final Beneficios beneficios = beneficiosRepository.findById(idBen)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(beneficiosDTO, beneficios);
        beneficiosRepository.save(beneficios);
    }

    public void delete(final Long idBen) {
        beneficiosRepository.deleteById(idBen);
    }
    
   public EcReturn uploadSk(SkFormatoDTO skFormato) {
    	
    	try {
    		
    		EcReturn vl_return = new EcReturn();
    		vl_return.setCorrectProcess(true);
    		
    		for(SkFormato beneficio :skFormato.getSk_formato()) {
    			
    			BeneficiosDTO beneficiosDTO=new BeneficiosDTO();
    			beneficiosDTO.setNombreBen(beneficio.getBeneficio());
    			beneficiosDTO.setTipoBen(getPropValues("skFormato"));
    			beneficiosDTO.setEstadoBen(new Long(0));
    			final Beneficios beneficios = new Beneficios();
    	        mapToEntity(beneficiosDTO, beneficios);
    	        beneficiosRepository.save(beneficios).getIdBen();
    	        beneficiosDTO.setIdBen(beneficios.getIdBen());
    		}
    		
    	     vl_return.setCorrectProcess(true);
    	     vl_return.setMessage("Datos guardados");
    		 vl_return.setData(skFormato);

    		return vl_return;
			
		} catch (Exception err) {
			 return ExceptionHandling.get(err);
		}
    }
   
   public EcReturn uploadSh(ThFormatoDTO thFormato) {
   	
	   try {
   		
   		EcReturn vl_return = new EcReturn();
   		vl_return.setCorrectProcess(true);
   		
   		for(String beneficio :thFormato.getBeneficios().getBeneficio()) {
   		  System.out.println(beneficio);
   		  
	   		BeneficiosDTO beneficiosDTO=new BeneficiosDTO();
			beneficiosDTO.setNombreBen(beneficio);
			beneficiosDTO.setTipoBen(getPropValues("thFormato"));
			beneficiosDTO.setEstadoBen(new Long(0));
			final Beneficios beneficios = new Beneficios();
	        mapToEntity(beneficiosDTO, beneficios);
	        beneficiosRepository.save(beneficios).getIdBen();
	        beneficiosDTO.setIdBen(beneficios.getIdBen());
   		}
   		
   	     vl_return.setCorrectProcess(true);
   	     vl_return.setMessage("Datos guardados");
   		 vl_return.setData(thFormato);

   		return vl_return;
			
		} catch (Exception err) {
			 return ExceptionHandling.get(err);
		}
   }

    static BeneficiosDTO mapToDTO(final Beneficios beneficios, final BeneficiosDTO beneficiosDTO) {
        beneficiosDTO.setIdBen(beneficios.getIdBen());
        beneficiosDTO.setNombreBen(beneficios.getNombreBen());
        beneficiosDTO.setTipoBen(beneficios.getTipoBen());
        beneficiosDTO.setEstadoBen(beneficios.getEstadoBen());
        return beneficiosDTO;
    }

    static Beneficios mapToEntity(final BeneficiosDTO beneficiosDTO, final Beneficios beneficios) {
        beneficios.setNombreBen(beneficiosDTO.getNombreBen());
        beneficios.setTipoBen(beneficiosDTO.getTipoBen());
        beneficios.setEstadoBen(beneficiosDTO.getEstadoBen());
        return beneficios;
    }
    
    public String getPropValues(String buscar ) throws IOException {
    	
		
		
    	String result = "";
        try {
        	
        	ClassPathResource resource = new ClassPathResource("/application.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            result=props.getProperty(buscar);
            System.out.println("valor: " + result);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return result;
    }

}
