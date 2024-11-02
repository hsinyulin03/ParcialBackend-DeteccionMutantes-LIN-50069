package org.example.mutant.services;

import org.example.mutant.dtos.DTOStats;
import org.example.mutant.repositories.DnaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class StatsServiceTest {

    //crea el mock del repositorio.
    @Mock
    private DnaRepository dnaRepository;

    //crea una instancia real del servicio y asigna automáticamente el mock dnaRepository a la dependencia de StatsService.
    @InjectMocks
    private StatsService statsService;


     //@BeforeEach hace que se ejecuta antes de cada prueba
     //setup() inicializa todo al principio de cada prueba con MockitoAnnotations.openMocks(this);, asegurando que los mocks estén listos y el servicio tenga las dependencias necesarias.
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    //situación en la que hay más humanos que mutantes.
    public void testGetStats_masHumanosqueMutantes() {
        // Simula que hay 50 mutantes y 150 humanos en la base de datos
        when(dnaRepository.countByIsMutant(true)).thenReturn(50L);
        when(dnaRepository.countByIsMutant(false)).thenReturn(150L);

        DTOStats stats = statsService.getStats();

        // Verifica que los valores coinciden con los configurados y el ratio esperado
        assertEquals(50L, stats.getCountMutantDna());    // Mutantes hay 50
        assertEquals(150L, stats.getCountHumanDna());    // Humanos hay 150
        assertEquals(0.333, stats.getRatio(), 0.001);    // Ratio: 50 / 150 = 0.333
    }

    @Test
    //simula cuando hay igual número de mutantes y humanos.
    public void testGetStats_igualNumerodeMutantesyHumanos() {
        // Simula que hay 100 mutantes y 100 humanos en la base de datos
        when(dnaRepository.countByIsMutant(true)).thenReturn(100L);
        when(dnaRepository.countByIsMutant(false)).thenReturn(100L);

        DTOStats stats = statsService.getStats();

        // Verifica que los valores coinciden con los configurados y el ratio esperado
        assertEquals(100L, stats.getCountMutantDna());   // Mutantes hay 100
        assertEquals(100L, stats.getCountHumanDna());    // Humanos hay 100
        assertEquals(1.0, stats.getRatio());             // Ratio: 100 / 100 = 1.0
    }

    @Test
    //situación en la que hay más mutantes que humanos.
    public void testGetStats_masMutantesqueHumanos() {
        // Simula que hay 200 mutantes y 50 humanos en la base de datos
        when(dnaRepository.countByIsMutant(true)).thenReturn(200L);
        when(dnaRepository.countByIsMutant(false)).thenReturn(50L);

        DTOStats stats = statsService.getStats();

        // Verifica que los valores coinciden con los configurados y el ratio esperado
        assertEquals(200L, stats.getCountMutantDna());   // Mutantes hay 200
        assertEquals(50L, stats.getCountHumanDna());     // Humanoshay 50
        assertEquals(4.0, stats.getRatio());             // Ratio: 200 / 50 = 4.0
    }

    @Test
    //situacion en donde solo hay mutantes
    public void testGetStats_SoloMutantes() {
        // Simula que solo hay mutantes y ningún humano
        when(dnaRepository.countByIsMutant(true)).thenReturn(10L);
        when(dnaRepository.countByIsMutant(false)).thenReturn(0L);

        DTOStats stats = statsService.getStats();

        assertEquals(10L, stats.getCountMutantDna());
        assertEquals(0L, stats.getCountHumanDna());
        assertEquals(0.0, stats.getRatio());
    }

    @Test
    //situacion en donde no hay datos en el repositorio
    public void testGetStats_sinDatosenRepo() {
        // Simula que no hay datos en la base de datos
        when(dnaRepository.countByIsMutant(true)).thenReturn(0L);
        when(dnaRepository.countByIsMutant(false)).thenReturn(0L);

        DTOStats stats = statsService.getStats();

        assertEquals(0L, stats.getCountMutantDna());
        assertEquals(0L, stats.getCountHumanDna());
        assertEquals(0.0, stats.getRatio());
    }
}
