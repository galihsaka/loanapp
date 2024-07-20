package com.enigma.repository;

import com.enigma.entity.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType, String> {
    Optional<LoanType> findByType(String type);
}
