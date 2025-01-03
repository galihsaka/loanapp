package com.enigma.repository;

import com.enigma.entity.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, String> {
    Optional<ProfilePicture> findByName(String name);
}
