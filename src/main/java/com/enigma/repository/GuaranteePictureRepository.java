package com.enigma.repository;

import com.enigma.entity.GuaranteePicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuaranteePictureRepository extends JpaRepository<GuaranteePicture, String> {
    Optional<GuaranteePicture> findByName(String name);
}
