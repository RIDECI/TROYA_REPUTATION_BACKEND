package edu.dosw.rideci.application.service;

import java.util.List;

import edu.dosw.rideci.application.port.in.rating.*;
import org.springframework.stereotype.Service;

import edu.dosw.rideci.application.port.out.PortRatingRepository;
import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Rating;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingService implements CalculateAverageReputationUseCase,DeleteCommentsAdminUseCase, GetAllCommentsUseCase,
                GetCommentByIdUseCase, GetFullReputationHistoryUseCase,CreateRatingUseCase,GetRatingUseCase,
                GetTripReputationDetailUseCase,GetUserBadgesUseCase,
                ListAllCommentsUseCase, CalculateTripRatingUseCase {

    private final PortRatingRepository portReputationRepository;


    @Override
    public List<String> listAllComments(Long userId) {
        return portReputationRepository.listAllComments(userId);
    }

    @Override
    public List<Badge> getBadgesForUser(Long userId) {
        return portReputationRepository.getBadgesForUser(userId);
    }

    @Override
    public List<Rating> getRatingsForTripId(Long tripId) {
        return portReputationRepository.getRatingsForTripId(tripId);
    }

    @Override
    public Rating getRatingById(Long ratingId) {
        return portReputationRepository.getRatingById(ratingId);
    }

    @Override
    public List<Rating> getReputationHistory(Long userId) {
        return portReputationRepository.getReputationHistory(userId);
    }

    @Override
    public Rating getCommentById(String commentId) {
        return portReputationRepository.getCommentById(commentId);
    }

    @Override
    public List<Rating> getAllCommentsByProfile(Long userId) {
        return portReputationRepository.getCommentsByProfile(userId);
    }

    @Override
    public void deleteComment(String commentId) {
        portReputationRepository.deleteComment(commentId);
    }

    @Override
    public void deleteAllCommentsByProfile(Long userId) {
        portReputationRepository.deleteAllCommentsByProfile(userId);
    }

    @Override
    public double calculateAverageReputation(Long userId) {
        return portReputationRepository.calculateAverageReputation(userId);
    }
    @Override
    public double calculateTripRating(Long tripId) {
        return portReputationRepository.calculateTripRating(tripId);
    }
    

    @Override
    public double calculateSimpleTripRating(Long tripId) {
        return portReputationRepository.calculateSimpleTripRating(tripId);
    }

    @Override
    public Rating createRating(Rating rating) {
        Rating savedRating = portReputationRepository.createRating(rating);
        //Cuando este implementado
        //calculateAverageReputation(rating.getTargetProfileId());
        //assignBadgeUseCase.assignBadge(rating.getTargetProfileId());
        return savedRating;
    }

    
}
