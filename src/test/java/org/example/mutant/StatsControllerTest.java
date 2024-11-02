package org.example.mutant;
import org.example.mutant.controllers.StatsController;
import org.example.mutant.dtos.DTOStats;
import org.example.mutant.services.StatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class StatsControllerTest {

    private StatsService statsService;
    private StatsController statsController;

    @BeforeEach
    void setUp() {
        statsService = Mockito.mock(StatsService.class);
        statsController = new StatsController(statsService);
    }

    @Test
    void testGetAll_ReturnsStats() {
        // Arrange
        DTOStats statsDto = new DTOStats(5, 10, 0.5);
        when(statsService.getStats()).thenReturn(statsDto);

        // Act
        ResponseEntity<?> response = statsController.getAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(statsDto, response.getBody());
    }

    @Test
    void testGetAll_ErrorHandling() {
        // Arrange
        when(statsService.getStats()).thenThrow(new RuntimeException("Error"));

        // Act
        ResponseEntity<?> response = statsController.getAll();

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("{\"error\":\"Error, por favor intente más tarde\"}", response.getBody());
    }
}
