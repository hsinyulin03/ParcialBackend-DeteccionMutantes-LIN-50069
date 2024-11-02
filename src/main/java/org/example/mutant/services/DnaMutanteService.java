package org.example.mutant.services;

import org.example.mutant.entities.Dna;
import org.example.mutant.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DnaMutanteService {
    private DnaRepository dnaRepository;

    @Autowired
    public DnaMutanteService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public boolean detectarMutante(Dna entity) throws Exception{
        try {
            // Llamar al método para validar el DNA ingresado
            validarDna(entity);
            // Lógica para detectar si es mutante
            //Verificamos si el ADN ya existe en la base de datos
            Optional<Dna> dnaExistente=dnaRepository.findByDna(entity.getDna());
            if(dnaExistente.isPresent()){
                return dnaExistente.get().isMutant();
            }

            boolean isMutant = esMutante(entity.getDna());
            // Guardar el ADN verificado en la base de datos
            entity.setMutant(isMutant);
            dnaRepository.save(entity);
            return isMutant;

        } catch (Exception e) {
            throw new Exception( e.getMessage());
        }
    }


    // Metodo para validar que el DNA ingresado sea válido
    private void validarDna(Dna entity){

        if (entity == null || entity.getDna() == null) {
            throw new IllegalArgumentException("El array de ADN no puede ser null.");
        }
        if (entity.getDna().length == 0) {
            throw new IllegalArgumentException("El array de ADN no puede estar vacío.");
        }
        int n = entity.getDna().length;
        // Verifica que el tamaño de cada fila sea correcto y que todos los caracteres sean válidos
        for (String fila : entity.getDna()) {
            if (fila == null) {
                throw new IllegalArgumentException("Cada fila del ADN no puede ser null.");
            }
            if (fila.length() != n) {
                throw new IllegalArgumentException("El array debe ser de tamaño NxN.");
            }
            for (char c : fila.toCharArray()) {
                if (Character.isDigit(c)) {
                    throw new IllegalArgumentException("El ADN no puede contener números");
                }
                if (c != 'A' && c != 'T' && c != 'C' && c != 'G') {
                    throw new IllegalArgumentException("El ADN solo puede contener las letras A, T, C, G.");
                }

            }
        }
    }
    public boolean esMutante(String[] dna) {
        int n = dna.length;
        //si la matriz es menor a 4x4 no hay posibilidad de que se detecte mutante y además si cumple las condiciones del metodo validarDna quiere decir que es un dna de humano
        if (n < 4) {
            return false;
        }
        //Inicializo variable
        int contador_secuencia = 0;

        //LLamo a distintas funciones para analizar si se logra encontrar más de una secuencia de cuatro letras
        //iguales, de forma oblicua, horizontal o vertical.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (horizontal(dna, i, j) ||
                        vertical(dna, i, j) ||
                        diagonalIzquierdo(dna, i, j) ||
                        diagonalDerecho(dna, i, j)) {

                    contador_secuencia++;

                    //Número mínimo de secuencias para considerar si un humano es mutante
                    if (contador_secuencia >1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    //FUNCIONES QUE AYUDAN A ANALIZAR SI UN HUMANO ES MUTANTE
    // Verificación horizontal
    private boolean horizontal(String[] dna, int i, int j) {
        int n = dna.length;
        if (j + 3 < n) {
            return dna[i].charAt(j) == dna[i].charAt(j + 1) &&
                    dna[i].charAt(j + 1) == dna[i].charAt(j + 2) &&
                    dna[i].charAt(j + 2) == dna[i].charAt(j + 3);
        }
        return false;
    }

    // Verificación vertical
    private boolean vertical(String[] dna, int i, int j) {
        int n = dna.length;
        if (i + 3 < n) {
            return dna[i].charAt(j) == dna[i + 1].charAt(j) &&
                    dna[i + 1].charAt(j) == dna[i + 2].charAt(j) &&
                    dna[i + 2].charAt(j) == dna[i + 3].charAt(j);
        }
        return false;
    }

    // Verificación diagonal de izquierda a derecha
    private boolean diagonalIzquierdo(String[] dna, int i, int j) {
        int n = dna.length;
        if (i + 3 < n && j + 3 < n) {
            return dna[i].charAt(j) == dna[i + 1].charAt(j + 1) &&
                    dna[i + 1].charAt(j + 1) == dna[i + 2].charAt(j + 2) &&
                    dna[i + 2].charAt(j + 2) == dna[i + 3].charAt(j + 3);
        }
        return false;
    }

    // Verificación diagonal de derecha a izquierda
    private boolean diagonalDerecho(String[] dna, int i, int j) {
        int n = dna.length;
        if (i + 3 < n && j - 3 >= 0) {
            return dna[i].charAt(j) == dna[i + 1].charAt(j - 1) &&
                    dna[i + 1].charAt(j - 1) == dna[i + 2].charAt(j - 2) &&
                    dna[i + 2].charAt(j - 2) == dna[i + 3].charAt(j - 3);

        }
        return false;
    }

}
