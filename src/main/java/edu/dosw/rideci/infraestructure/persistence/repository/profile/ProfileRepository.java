package edu.dosw.rideci.infraestructure.persistence.repository.profile;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.dosw.rideci.infraestructure.persistence.entity.ProfileDocument;

public interface ProfileRepository extends MongoRepository<ProfileDocument, String> {
    Optional<ProfileDocument> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
