package edu.dosw.rideci.infraestructure.persistence.repository.rating;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import edu.dosw.rideci.application.port.out.PortRatingRepository;
import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Rating;
import edu.dosw.rideci.exceptions.RatingNotFoundException;
import edu.dosw.rideci.infraestructure.persistence.entity.RatingDocument;
import edu.dosw.rideci.infraestructure.persistence.repository.mapper.ProfileMapper;
import edu.dosw.rideci.infraestructure.persistence.repository.mapper.RatingMapper;
import edu.dosw.rideci.infraestructure.persistence.repository.profile.ProfileRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RatingRepositoryAdapter implements PortRatingRepository {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    //Revisar
    @Override
    public double calculateAverageReputation(Long profileId) {
        throw new UnsupportedOperationException("Unimplemented method 'getRatingsByProfile'");

    }

    @Override
    public Rating getRatingById(Long ratingId) {
        RatingDocument ratingDocument = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new RatingNotFoundException("Rating not found"));
        return ratingMapper.toDomain(ratingDocument);
    }

    @Override
    public List<Rating> getRatingsByProfile(Long profileId) {
        List<RatingDocument> docs = ratingRepository.findAllByTargetId(profileId);
        return ratingMapper.toListDomain(docs);
    }

    //Revisar
    @Override
    public Rating getCommentById(Long commentId) {
        return getRatingById(commentId);
    }

    //Revisar
    @Override
    public List<Rating> getReputationHistory(Long profileId) {
        return getRatingsByProfile(profileId);
    }

    //Revisar
    @Override
    public List<Rating> getCommentsByProfile(Long profileId) {
        return getRatingsByProfile(profileId);
    }

    @Override
    public List<Rating> getRatingsForTripId(Long tripId) {
        List<RatingDocument> docs = ratingRepository.findAllByTripId(tripId);
        return ratingMapper.toListDomain(docs);
    }

    //Falta implementar los badges
    @Override
    public List<Badge> getBadgesForUser(Long profileId) {
        throw new UnsupportedOperationException("Unimplemented method 'getBadgesForUser'");
    }

    @Override
    public List<String> listAllComments(Long profileId) {
        List<RatingDocument> docs = ratingRepository.findAllByTargetId(profileId);

        return docs.stream()
                .map(RatingDocument::getComment)       
                .filter(comment -> comment != null && !comment.isEmpty()) 
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        ratingRepository.deleteById(commentId);
    }

    @Override
    public void deleteAllCommentsByProfile(Long profileId) {
        ratingRepository.deleteByTargetId(profileId);
    }
    
}
