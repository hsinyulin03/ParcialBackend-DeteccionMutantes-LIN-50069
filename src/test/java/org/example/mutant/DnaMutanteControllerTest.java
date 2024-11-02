package org.example.mutant;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.mutant.controllers.DnaMutanteController;
import org.example.mutant.services.DnaMutanteService;
import org.example.mutant.entities.Dna;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DnaMutanteControllerTest {

    @Mock
    private DnaMutanteService dnaMutanteService;

    @InjectMocks
    private DnaMutanteController dnaMutanteController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(dnaMutanteController).build();
    }

    @Test
    void testValidMutantMatrix() throws Exception {
        String[] dna = {
                "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG",
        };

        Dna dnaEntity = new Dna();
        dnaEntity.setDna(dna);

        when(dnaMutanteService.detectarMutante(any(Dna.class))).thenReturn(true);

        String dnaJson = objectMapper.writeValueAsString(dnaEntity);

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dnaJson))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Mutante detectado\"}"));
    }

    @Test
    void testInvalidMutantDnaFormatNxM() throws Exception {
        String[] dna = {
                "TTGCGA","GCTGTA","GCTGTA","ATGCAA","GAGGAC","GAGGAC",
        };

        Dna dnaEntity = new Dna();
        dnaEntity.setDna(dna);

        when(dnaMutanteService.detectarMutante(any(Dna.class))).thenReturn(false);

        String dnaJson = objectMapper.writeValueAsString(dnaEntity);

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dnaJson))
                .andExpect(status().isForbidden())
                .andExpect(content().json("{\"message\":\"No es mutante\"}"));
    }

    @Test
    void testInvalidMutantDnaFormatNull() throws Exception {
        String[] dna = null;

        Dna dnaEntity = new Dna();
        dnaEntity.setDna(dna);

        String dnaJson = objectMapper.writeValueAsString(dnaEntity);

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dnaJson))
                .andExpect(status().isForbidden());
    }
    @Test
    void testExceptionHandling() throws Exception {
        String[] dna = {
                "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"
        };

        Dna dnaEntity = new Dna();
        dnaEntity.setDna(dna);

        when(dnaMutanteService.detectarMutante(any(Dna.class))).thenThrow(new RuntimeException("Error"));

        String dnaJson = objectMapper.writeValueAsString(dnaEntity);

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dnaJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Error\"}"));
    }
}