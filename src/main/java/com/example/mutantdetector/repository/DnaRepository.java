package com.example.mutantdetector.repository;

import com.example.mutantdetector.model.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaRepository extends JpaRepository<DnaRecord, Long> {
    long countByMutant(boolean mutant);
}