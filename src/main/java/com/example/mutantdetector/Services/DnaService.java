package com.example.mutantdetector.service;

import com.example.mutantdetector.exception.InvalidDnaException;
import com.example.mutantdetector.model.DnaRecord;
import com.example.mutantdetector.repository.DnaRepository;
import org.springframework.stereotype.Service;

@Service
public class DnaService {

    private final DnaRepository dnaRepository;

    public DnaService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public boolean isMutant(String[] dna) {
        if (dna == null || dna.length == 0) {
            throw new InvalidDnaException("El ADN no puede estar vacío");
        }

        int n = dna.length;
        for (String row : dna) {
            if (row.length() != n) {
                throw new InvalidDnaException("El ADN debe ser una matriz cuadrada (NxN)");
            }
            if (!row.matches("[ATCG]+")) {
                throw new InvalidDnaException("El ADN contiene caracteres no válidos");
            }
        }

        int count = 0;

        // Verificar secuencias horizontales
        for (int i = 0; i < n; i++) {
            if (hasSequence(dna[i])) {
                count++;
            }
        }

        // Verificar secuencias verticales
        for (int j = 0; j < n; j++) {
            StringBuilder vertical = new StringBuilder();
            for (int i = 0; i < n; i++) {
                vertical.append(dna[i].charAt(j));
            }
            if (hasSequence(vertical.toString())) {
                count++;
            }
        }

        // Verificar secuencias diagonales
        for (int i = 0; i < n - 3; i++) {
            for (int j = 0; j < n - 3; j++) {
                if (hasDiagonalSequence(dna, i, j)) {
                    count++;
                }
            }
        }

        boolean isMutant = count > 1;
        saveDnaRecord(dna, isMutant);
        return isMutant;
    }

    private boolean hasSequence(String sequence) {
        return sequence.contains("AAAA") || sequence.contains("TTTT") ||
                sequence.contains("CCCC") || sequence.contains("GGGG");
    }

    private boolean hasDiagonalSequence(String[] dna, int x, int y) {
        char base = dna[x].charAt(y);
        return dna[x + 1].charAt(y + 1) == base &&
                dna[x + 2].charAt(y + 2) == base &&
                dna[x + 3].charAt(y + 3) == base;
    }

    private void saveDnaRecord(String[] dna, boolean isMutant) {
        String dnaString = String.join("", dna);
        DnaRecord dnaRecord = new DnaRecord();
        dnaRecord.setDna(dnaString);
        dnaRecord.setMutant(isMutant);
        dnaRepository.save(dnaRecord);
    }
}