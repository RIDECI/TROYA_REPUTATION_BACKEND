package edu.dosw.rideci.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.dosw.rideci.application.port.in.rating.CalculateAverageReputationUseCase;
import edu.dosw.rideci.application.port.in.rating.CreateRatingUseCase;
import edu.dosw.rideci.application.port.in.rating.DeleteCommentsAdminUseCase;
import edu.dosw.rideci.application.port.in.rating.GetAllCommentsUseCase;
import edu.dosw.rideci.application.port.in.rating.GetCommentByIdUseCase;
import edu.dosw.rideci.application.port.in.rating.GetFullReputationHistoryUseCase;
import edu.dosw.rideci.application.port.in.rating.GetRatingUseCase;
import edu.dosw.rideci.application.port.in.rating.GetTripReputationDetailUseCase;
import edu.dosw.rideci.application.port.in.rating.GetUserBadgesUseCase;
import edu.dosw.rideci.application.port.in.rating.ListAllCommentsUseCase;
import edu.dosw.rideci.application.port.out.PortRatingRepository;
import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Rating;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingService implements CalculateAverageReputationUseCase,DeleteCommentsAdminUseCase, GetAllCommentsUseCase,
                GetCommentByIdUseCase, GetFullReputationHistoryUseCase,CreateRatingUseCase,GetRatingUseCase,
                GetTripReputationDetailUseCase,GetUserBadgesUseCase,
                ListAllCommentsUseCase {

    private final PortRatingRepository portReputationRepository;


    @Override
    public List<String> listAllComments(Long profileId) {
        return portReputationRepository.listAllComments(profileId);
    }

    @Override
    public List<Badge> getBadgesForUser(Long profileId) {
        return portReputationRepository.getBadgesForUser(profileId);
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
    public List<Rating> getReputationHistory(Long profileId) {
        return portReputationRepository.getReputationHistory(profileId);
    }

    @Override
    public Rating getCommentById(Long commentId) {
        return portReputationRepository.getCommentById(commentId);
    }

    @Override
    public List<Rating> getAllCommentsByProfile(Long profileId) {
        return portReputationRepository.getCommentsByProfile(profileId);
    }

    @Override
    public void deleteComment(Long commentId) {
        portReputationRepository.deleteComment(commentId);
    }

    @Override
    public void deleteAllCommentsByProfile(Long profileId) {
        portReputationRepository.deleteAllCommentsByProfile(profileId);
    }

    @Override
    public double calculateAverageReputation(Long profileId) {
        return portReputationRepository.calculateAverageReputation(profileId);
    }

    @Override
    public Rating createRating(Rating rating) {
        return portReputationRepository.createRating(rating);
    }

    
    
}
