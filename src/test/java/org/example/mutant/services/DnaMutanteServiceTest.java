package org.example.mutant.services;

import org.example.mutant.entities.Dna;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest

public class DnaMutanteServiceTest {
    @Autowired
    private DnaMutanteService dnaMutanteService;


    @Test
    public void testDetectarMutante_EsMutante() throws Exception{
        //Dato de prueba
        String[]dna={"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        Dna entity=new Dna(dna);

        //Ejecución de la lógica
        boolean resultado=dnaMutanteService.detectarMutante(entity);

        //Verificación
        assertTrue(resultado);
    }



@Test
public void testDetectarMutante_NoEsMutante() throws Exception {
    // Dato de prueba ADN que no es mutante
    String[] dna = {"TTGCGA", "GCTGTA", "GCTGTA", "ATGCAA", "GAGGAC", "GAGGAC"};
    Dna entity = new Dna(dna);

    // Ejecución de la lógica
    boolean resultado = dnaMutanteService.detectarMutante(entity);

    // Verificación
    assertFalse(resultado);  // Debe ser false porque no es mutante
  }
    @Test
    void testDetectarMutante_arrayVacio()throws Exception {
        String[] dna = {};// Simulamos un array vacio
        Dna entity = new Dna(dna);
        Exception exception = assertThrows(Exception.class, () -> {
            dnaMutanteService.detectarMutante(entity);
        });

        Assertions.assertEquals("El array de ADN no puede estar vacío.", exception.getMessage());
    }
    @Test
    public void testDetectarMutante_arrayNxM() throws Exception {
        // Dato de prueba ADN que no es de NXN
        String[] dna = {"ATG", "CAGT", "TTAG"};
        Dna entity = new Dna(dna);

        // Verifica que se lance una excepción
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            dnaMutanteService.detectarMutante(entity);
        });

        // Verificación del mensaje de error
        Assertions.assertEquals("El array debe ser de tamaño NxN.", exception.getMessage());
        System.out.println(exception.getMessage());
    }

    @Test
    void testDetectarMutante_arrayNumeros() throws Exception{
        String[] dna = {"1111", "3456", "3421", "4111"};// Contiene números
        Dna entity = new Dna(dna);

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            dnaMutanteService.detectarMutante(entity);
        });

        Assertions.assertEquals("El ADN no puede contener números", exception.getMessage());
    }
    @Test
    void testDetectarMutante_valorNulo() throws Exception {
        String[] dna = {null};
        Dna entity = new Dna(null);// Simulamos un array null

        Exception exception = assertThrows(Exception.class, () -> {
            dnaMutanteService.detectarMutante(entity);
        });

        assertEquals("El array de ADN no puede ser null.", exception.getMessage());
    }

    @Test
    void testDetectarMutante_arrayNxNNulas() throws Exception {
        // Creamos un array NxN (por ejemplo, 4x4) donde cada fila es null
        String[] dna = {null, null, null, null};
        Dna entity = new Dna(dna);

        // Verificamos que se lanza la excepción correcta
        Exception exception = assertThrows(Exception.class, () -> {
            dnaMutanteService.detectarMutante(entity);
        });

        assertEquals("Cada fila del ADN no puede ser null.", exception.getMessage());
    }

    @Test
    public void testDetectarMutante_letrasDistintas() throws Exception {
        // Dato de prueba ADN con letras distintas a las propuestas
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTB"};
        Dna entity = new Dna(dna);

        // Verifica que se lance una excepción
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            dnaMutanteService.detectarMutante(entity);
        });

        // Verificación del mensaje de error
        Assertions.assertEquals("El ADN solo puede contener las letras A, T, C, G.", exception.getMessage());
        System.out.println(exception.getMessage());
    }
        @Test
        public void testEsMutante_matrizTamañoMenor4() {
            // Matriz de tamaño 1x1
            String[] dna1 = {"A"};
            assertFalse(dnaMutanteService.esMutante(dna1));

            // Matriz de tamaño 2x2
            String[] dna2 = {"AT", "CG"};
            assertFalse(dnaMutanteService.esMutante(dna2));

            // Matriz de tamaño 3x3
            String[] dna3 = {"ATC", "GTA", "CGA"};
            assertFalse(dnaMutanteService.esMutante(dna3));
        }
    }


