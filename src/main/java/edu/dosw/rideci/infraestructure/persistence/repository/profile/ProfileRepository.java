package edu.dosw.rideci.infraestructure.persistence.repository.profile;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.dosw.rideci.infraestructure.persistence.entity.ProfileDocument;

public interface ProfileRepository extends MongoRepository<ProfileDocument, Long> {
    
}
