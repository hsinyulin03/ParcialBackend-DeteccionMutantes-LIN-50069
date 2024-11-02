package org.example.mutant.services;

import org.example.mutant.dtos.DTOStats;
import org.example.mutant.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    private final DnaRepository dnaRepository;

    @Autowired
    public StatsService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public DTOStats getStats(){
        long mutantCount = dnaRepository.countByIsMutant(true);
        long humanCount =dnaRepository.countByIsMutant(false);
        double ratio;
        if (humanCount > 0) {
            ratio = (double) mutantCount / humanCount;
        } else {
            ratio = 0.0;
        }
        return new DTOStats(mutantCount, humanCount,ratio);
    }
}
