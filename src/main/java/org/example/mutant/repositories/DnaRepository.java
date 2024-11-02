package org.example.mutant.repositories;

import org.example.mutant.entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DnaRepository extends JpaRepository<Dna, Long>{
    Optional<Dna> findByDna(String[] secuenciaDna);

    long countByIsMutant(boolean detectarMutante);
}
