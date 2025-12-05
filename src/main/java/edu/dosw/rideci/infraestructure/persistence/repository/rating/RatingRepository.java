package edu.dosw.rideci.infraestructure.persistence.repository.rating;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.dosw.rideci.infraestructure.persistence.entity.RatingDocument;

public interface RatingRepository extends MongoRepository<RatingDocument, String> {
    List<RatingDocument> findAllByTargetId(Long targetId);
    List<RatingDocument> findAllByTripId(Long tripId);
    long countByTargetId(Long targetId);
    void deleteByTargetId(Long targetId);
    RatingDocument findByRatingId(Long ratingId);
}
