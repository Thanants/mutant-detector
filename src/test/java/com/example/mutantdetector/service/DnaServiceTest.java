package com.example.mutantdetector.service;

import com.example.mutantdetector.exception.InvalidDnaException;
import com.example.mutantdetector.repository.DnaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DnaServiceTest {

    @Mock
    private DnaRepository dnaRepository;

    @InjectMocks
    private com.example.mutantdetector.service.DnaService dnaService;

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