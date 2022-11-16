package io.danny.back_end.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.danny.back_end.config.BaseIT;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;


public class BeneficiosResourceTest extends BaseIT {

    @Test
    @Sql("/data/beneficiosData.sql")
    public void getAllBeneficioss_success() throws Exception {
        mockMvc.perform(get("/api/beneficios")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idBen").value(((long)1100)));
    }

    @Test
    @Sql("/data/beneficiosData.sql")
    public void getBeneficios_success() throws Exception {
        mockMvc.perform(get("/api/beneficios/1100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreBen").value("Aenean pulvinar..."));
    }

    @Test
    public void getBeneficios_notFound() throws Exception {
        mockMvc.perform(get("/api/beneficios/1766")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.exception").value("ResponseStatusException"));
    }

    @Test
    public void createBeneficios_success() throws Exception {
        mockMvc.perform(post("/api/beneficios")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(readResource("/requests/beneficiosDTORequest.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        assertEquals(1, beneficiosRepository.count());
    }

    @Test
    public void createBeneficios_missingField() throws Exception {
        mockMvc.perform(post("/api/beneficios")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(readResource("/requests/beneficiosDTORequest_missingField.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.exception").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.fieldErrors[0].field").value("nombreBen"));
    }

    @Test
    @Sql("/data/beneficiosData.sql")
    public void updateBeneficios_success() throws Exception {
        mockMvc.perform(put("/api/beneficios/1100")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(readResource("/requests/beneficiosDTORequest.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals("Ut pellentesque sapien...", beneficiosRepository.findById(((long)1100)).get().getNombreBen());
        assertEquals(1, beneficiosRepository.count());
    }

    @Test
    @Sql("/data/beneficiosData.sql")
    public void deleteBeneficios_success() throws Exception {
        mockMvc.perform(delete("/api/beneficios/1100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        assertEquals(0, beneficiosRepository.count());
    }

}
