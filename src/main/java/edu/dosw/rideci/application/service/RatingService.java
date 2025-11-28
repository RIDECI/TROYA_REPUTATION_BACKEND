package edu.dosw.rideci.application.service;

import java.time.LocalDateTime;
import java.util.List;

import edu.dosw.rideci.application.events.RatingCreatedEvent;
import edu.dosw.rideci.application.port.in.rating.*;
import edu.dosw.rideci.application.port.out.EventPublisher;
import org.springframework.stereotype.Service;

import edu.dosw.rideci.application.port.out.PortRatingRepository;
import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Rating;
import lombok.RequiredArgsConstructor;

import static edu.dosw.rideci.infraestructure.config.RabbitConfig.*;

@Service
@RequiredArgsConstructor
public class RatingService implements CalculateAverageReputationUseCase,DeleteCommentsAdminUseCase, GetAllCommentsUseCase,
                GetCommentByIdUseCase, GetFullReputationHistoryUseCase,CreateRatingUseCase,GetRatingUseCase,
                GetTripReputationDetailUseCase,GetUserBadgesUseCase,
                ListAllCommentsUseCase, CalculateTripRatingUseCase {

    private final PortRatingRepository portReputationRepository;
    private final EventPublisher eventPublisher;


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
    public double calculateTripRating(Long tripId) {
        double weightedRating = portReputationRepository.calculateTripRating(tripId);

        // Publicar evento de viaje calificado
        List<Rating> ratings = portReputationRepository.getRatingsForTripId(tripId);
        if (!ratings.isEmpty()) {
            Rating lastRating = ratings.get(ratings.size() - 1);

            RatingCreatedEvent event = RatingCreatedEvent.builder()
                    .ratingId(lastRating.getId())
                    .tripId(tripId)
                    .score(lastRating.getScore())
                    .createdAt(LocalDateTime.now())
                    .eventType("TRIP_RATED")
                    .build();

            eventPublisher.publish(event, EXCHANGE_PROFILE, RATING_TRIP_ROUTING_KEY);
            System.out.println("Evento publicado - Viaje calificado: " + tripId + " | Rating: " + weightedRating);
        }

        return weightedRating;
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

    public void publishUserRatingCreated(Rating rating) {
        RatingCreatedEvent event = RatingCreatedEvent.builder()
                .ratingId(rating.getId())
                .raterProfileId(rating.getRaterId())
                .ratedProfileId(rating.getTargetId())
                .tripId(rating.getTripId())
                .score(rating.getScore())
                .comment(rating.getComment())
                .createdAt(LocalDateTime.now())
                .eventType("USER_RATED")
                .build();

        eventPublisher.publish(event, EXCHANGE_PROFILE, RATING_USER_ROUTING_KEY);
        System.out.println("Evento publicado - Usuario calificado: " + rating.getTargetId());
    }
    
}
