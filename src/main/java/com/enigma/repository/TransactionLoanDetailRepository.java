package com.enigma.repository;

import com.enigma.entity.TransactionLoanDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLoanDetailRepository extends JpaRepository<TransactionLoanDetails, String> {
}
