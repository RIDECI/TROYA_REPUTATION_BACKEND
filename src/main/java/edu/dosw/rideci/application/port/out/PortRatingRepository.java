package edu.dosw.rideci.application.port.out;

import java.util.List;

import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Rating;

public interface PortRatingRepository {

    double calculateAverageReputation(Long profileId);
    Rating getRatingById(Long ratingId);
    List<Rating> getRatingsByProfile(Long profileId);
    Rating getCommentById(Long commentId);
    List<Rating> getReputationHistory(Long profileId);
    void deleteComment(Long commentId);
    void deleteAllCommentsByProfile(Long profileId);
    List<Rating> getCommentsByProfile(Long profileId);
    List<Rating> getRatingsForTripId(Long tripId);
    List<Badge> getBadgesForUser(Long profileId);
    List<String> listAllComments(Long profileId);
    Rating createRating(Rating rating);
    double calculateTripRating(Long tripId);
    double calculateSimpleTripRating(Long tripId);

}
