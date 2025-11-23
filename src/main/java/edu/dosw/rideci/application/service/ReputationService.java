package edu.dosw.rideci.application.service;

import java.util.HashMap;
import java.util.List;

import edu.dosw.rideci.domain.model.Profile;
import org.springframework.stereotype.Service;

import edu.dosw.rideci.application.port.in.rating.CalculateAverageReputationUseCase;
import edu.dosw.rideci.application.port.in.rating.DeleteCommentsAdminUseCase;
import edu.dosw.rideci.application.port.in.rating.GetAllCommentsUseCase;
import edu.dosw.rideci.application.port.in.rating.GetCommentByIdUseCase;
import edu.dosw.rideci.application.port.in.rating.GetFullReputationHistoryUseCase;
import edu.dosw.rideci.application.port.in.rating.GetRatingUseCase;
import edu.dosw.rideci.application.port.in.rating.GetTripReputationDetailUseCase;
import edu.dosw.rideci.application.port.in.rating.CalculateWeightedReputationUseCase;
import edu.dosw.rideci.application.port.in.rating.GetUserBadgesUseCase;
import edu.dosw.rideci.application.port.in.rating.ListAllCommentsUseCase;
import edu.dosw.rideci.application.port.out.PortRatingRepository;
import edu.dosw.rideci.application.port.out.PortProfileRepository;
import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Rating;
import edu.dosw.rideci.domain.model.Reputation;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReputationService implements CalculateAverageReputationUseCase,CalculateWeightedReputationUseCase,DeleteCommentsAdminUseCase, GetAllCommentsUseCase,
                GetCommentByIdUseCase, GetFullReputationHistoryUseCase,GetRatingUseCase,
                GetTripReputationDetailUseCase,GetUserBadgesUseCase,
                ListAllCommentsUseCase {

    private final PortRatingRepository portReputationRepository;
    private final PortProfileRepository portProfileRepository;


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
    public double calculateWeightedReputation(Long profileId) {
        Profile profile = portProfileRepository.getProfileById(profileId);
        Reputation rep = profile != null ? profile.getCalification() : null;

        // Definir hash con los pesos
        HashMap<Integer, Double> defaults = new HashMap<>();
        defaults.put(5, 0.9);
        defaults.put(4, 0.8);
        defaults.put(3, 0.7);
        defaults.put(2, 0.5);
        defaults.put(1, 0.5);

        HashMap<Integer, Double> weights = null;
        if (rep != null) {
            weights = rep.getWeightedScores();
        }

        if (weights == null || weights.isEmpty()) {
            weights = defaults;
        }

        //sumar key * value para cada entrada
        double sum = weights.entrySet().stream()
                .mapToDouble(e -> e.getKey() * e.getValue())
                .sum();

        return sum;
    }
    
}
