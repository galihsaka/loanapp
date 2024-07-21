package com.enigma.repository;

import com.enigma.entity.LoanDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanDocumentRepository extends JpaRepository<LoanDocument, String> {
    Optional<LoanDocument> findByName(String name);
}
