package io.danny.back_end.repos;

import io.danny.back_end.domain.Beneficios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BeneficiosRepository extends JpaRepository<Beneficios, Long> {
	
	 List<Beneficios> findByEstadoBenAndTipoBen(Long estadoBen, String tipoBen);
}
