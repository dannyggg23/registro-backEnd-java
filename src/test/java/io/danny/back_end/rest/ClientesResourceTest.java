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


public class ClientesResourceTest extends BaseIT {

    @Test
    @Sql({"/data/beneficiosData.sql", "/data/clientesData.sql"})
    public void getAllClientess_success() throws Exception {
        mockMvc.perform(get("/api/clientes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$[0].idCli").value(((long)1000)));
    }

    @Test
    @Sql({"/data/beneficiosData.sql", "/data/clientesData.sql"})
    public void getClientes_success() throws Exception {
        mockMvc.perform(get("/api/clientes/1000")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreCli").value("Donec ac nibh..."));
    }

    @Test
    public void getClientes_notFound() throws Exception {
        mockMvc.perform(get("/api/clientes/1666")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.exception").value("ResponseStatusException"));
    }

    @Test
    @Sql("/data/beneficiosData.sql")
    public void createClientes_success() throws Exception {
        mockMvc.perform(post("/api/clientes")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(readResource("/requests/clientesDTORequest.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        assertEquals(1, clientesRepository.count());
    }

    @Test
    public void createClientes_missingField() throws Exception {
        mockMvc.perform(post("/api/clientes")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(readResource("/requests/clientesDTORequest_missingField.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.exception").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.fieldErrors[0].field").value("nombreCli"));
    }

    @Test
    @Sql({"/data/beneficiosData.sql", "/data/clientesData.sql"})
    public void updateClientes_success() throws Exception {
        mockMvc.perform(put("/api/clientes/1000")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(readResource("/requests/clientesDTORequest.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals("Aenean pulvinar...", clientesRepository.findById(((long)1000)).get().getNombreCli());
        assertEquals(1, clientesRepository.count());
    }

    @Test
    @Sql({"/data/beneficiosData.sql", "/data/clientesData.sql"})
    public void deleteClientes_success() throws Exception {
        mockMvc.perform(delete("/api/clientes/1000")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        assertEquals(0, clientesRepository.count());
    }

}
