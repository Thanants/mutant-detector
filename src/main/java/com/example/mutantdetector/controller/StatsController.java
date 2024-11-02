package com.example.mutantdetector.controller;

import com.example.mutantdetector.dto.StatsResponse;
import com.example.mutantdetector.repository.DnaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final DnaRepository dnaRepository;

    public StatsController(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    @GetMapping
    public StatsResponse getStats() {
        long countMutantDna = dnaRepository.countByMutant(true);
        long countHumanDna = dnaRepository.countByMutant(false);
        double ratio = countHumanDna == 0 ? 0 : (double) countMutantDna / countHumanDna;

        return new StatsResponse(countMutantDna, countHumanDna, ratio);
    }
}