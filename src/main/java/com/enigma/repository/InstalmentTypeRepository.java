package com.enigma.repository;

import com.enigma.entity.InstalmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstalmentTypeRepository extends JpaRepository<InstalmentType, String> {
    Optional<InstalmentType> findByInstalmentType(InstalmentType.EInstalmentType instalmentType);
}
