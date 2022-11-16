package io.danny.back_end.repos;

import io.danny.back_end.domain.Beneficios;
import io.danny.back_end.domain.Clientes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientesRepository extends JpaRepository<Clientes, Long> {
	
	List<Clientes> findByNombreCliAndGrupoCli(String nombreCli, String grupoCli);
	
	boolean existsByNombreCliAndGrupoCliIgnoreCase(String nombreCli, String grupoCli);
	
}
