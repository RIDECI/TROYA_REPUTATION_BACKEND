package edu.dosw.rideci.application.port.out;

import java.util.List;

import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Rating;

public interface PortRatingRepository {

    double calculateAverageReputation(Long userId);
    Rating getRatingById(Long ratingId);
    List<Rating> getRatingsByProfile(Long userId);
    Rating getCommentById(String commentId);
    List<Rating> getReputationHistory(Long userId);
    void deleteComment(String commentId);
    void deleteAllCommentsByProfile(Long userId);
    List<Rating> getCommentsByProfile(Long userId);
    List<Rating> getRatingsForTripId(Long tripId);
    List<Badge> getBadgesForUser(Long userId);
    List<String> listAllComments(Long userId);
    Rating createRating(Rating rating);
    double calculateTripRating(Long tripId);
    double calculateSimpleTripRating(Long tripId);

}
