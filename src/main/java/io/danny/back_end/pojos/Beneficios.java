package io.danny.back_end.pojos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class Beneficios {

	@JacksonXmlElementWrapper(useWrapping = false)
	private List<String> beneficio = new ArrayList<>();
		
		
		public Beneficios() {
			
			
		}
		
		


		public Beneficios(List<String> beneficio) {
		
			this.beneficio = beneficio;
		}




		public List<String> getBeneficio() {
			return beneficio;
		}


		public void setBeneficio(List<String> beneficio) {
			this.beneficio = beneficio;
		}
		

	
		
	
}
