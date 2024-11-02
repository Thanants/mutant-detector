package com.example.mutantdetector.service;

import com.example.mutantdetector.exception.InvalidDnaException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DnaServiceTest {

    private final com.example.mutantdetector.service.DnaService dnaService = new com.example.mutantdetector.service.DnaService(null);

    @Test
    void testIsMutant() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        assertTrue(dnaService.isMutant(dna));
    }

    @Test
    void testIsNotMutant() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        assertFalse(dnaService.isMutant(dna));
    }

    @Test
    void testInvalidDna() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACT"};
        assertThrows(InvalidDnaException.class, () -> dnaService.isMutant(dna));
    }
}