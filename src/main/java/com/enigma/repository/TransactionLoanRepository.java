package com.enigma.repository;

import com.enigma.entity.TransactionLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLoanRepository extends JpaRepository<TransactionLoan, String> {
}
