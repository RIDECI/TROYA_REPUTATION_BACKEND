package edu.dosw.rideci.TROYA_REPUTATION_BACKEND.ServicesTest;

import edu.dosw.rideci.application.port.out.PortProfileRepository;
import edu.dosw.rideci.application.port.out.PortRatingRepository;
import edu.dosw.rideci.application.service.ReputationService;
import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.domain.model.Rating;
import edu.dosw.rideci.domain.model.Reputation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReputationServiceTest {

    @Mock
    private PortRatingRepository portRatingRepository;

    @Mock
    private PortProfileRepository portProfileRepository;

    @InjectMocks
    private ReputationService reputationService;

    // ---------------------------
    // TEST: listAllComments
    // ---------------------------
    @Test
    void TestListAllComments() {
        Long profileId = 1L;
        List<String> comments = List.of("Buen viaje", "Excelente conductor");

        when(portRatingRepository.listAllComments(profileId))
                .thenReturn(comments);

        List<String> result = reputationService.listAllComments(profileId);

        assertEquals(2, result.size());
        verify(portRatingRepository).listAllComments(profileId);
    }

    // ---------------------------
    // TEST: getBadgesForUser
    // ---------------------------
    @Test
    void TestGetBadgesForUser() {
        Long profileId = 1L;
        List<Badge> badges = List.of(new Badge(), new Badge());

        when(portRatingRepository.getBadgesForUser(profileId))
                .thenReturn(badges);

        List<Badge> result = reputationService.getBadgesForUser(profileId);

        assertEquals(2, result.size());
        verify(portRatingRepository).getBadgesForUser(profileId);
    }

    // ---------------------------
    // TEST: getRatingsForTripId
    // ---------------------------
    @Test
    void TestGetRatingsForTripId() {
        Long tripId = 10L;
        List<Rating> ratings = List.of(new Rating(), new Rating());

        when(portRatingRepository.getRatingsForTripId(tripId))
                .thenReturn(ratings);

        List<Rating> result = reputationService.getRatingsForTripId(tripId);

        assertEquals(2, result.size());
        verify(portRatingRepository).getRatingsForTripId(tripId);
    }

    // ---------------------------
    // TEST: getRatingById
    // ---------------------------
    @Test
    void TestGetRatingById() {
        Long ratingId = 7L;
        Rating rating = new Rating();
        rating.setId(ratingId);

        when(portRatingRepository.getRatingById(ratingId))
                .thenReturn(rating);

        Rating result = reputationService.getRatingById(ratingId);

        assertEquals(ratingId, result.getId());
        verify(portRatingRepository).getRatingById(ratingId);
    }


    // ---------------------------
    // TEST: getReputationHistory
    // ---------------------------
    @Test
    void TestGetReputationHistory() {
        Long profileId = 1L;
        List<Rating> list = List.of(new Rating(), new Rating());

        when(portRatingRepository.getReputationHistory(profileId))
                .thenReturn(list);

        List<Rating> result = reputationService.getReputationHistory(profileId);

        assertEquals(2, result.size());
        verify(portRatingRepository).getReputationHistory(profileId);
    }

    // ---------------------------
    // TEST: getCommentById
    // ---------------------------
    @Test
    void TestGetCommentById() {
        Long commentId = 100L;
        Rating rating = new Rating();

        when(portRatingRepository.getCommentById(commentId))
                .thenReturn(rating);

        Rating result = reputationService.getCommentById(commentId);

        assertNotNull(result);
        verify(portRatingRepository).getCommentById(commentId);
    }

    // ---------------------------
    // TEST: getAllCommentsByProfile
    // ---------------------------
    @Test
    void TestGetAllCommentsByProfile() {
        Long profileId = 1L;
        List<Rating> list = List.of(new Rating());

        when(portRatingRepository.getCommentsByProfile(profileId))
                .thenReturn(list);

        List<Rating> result = reputationService.getAllCommentsByProfile(profileId);

        assertEquals(1, result.size());
        verify(portRatingRepository).getCommentsByProfile(profileId);
    }

    // ---------------------------
    // TEST: deleteComment
    // ---------------------------
    @Test
    void TestDeleteComment() {
        Long commentId = 1L;

        reputationService.deleteComment(commentId);

        verify(portRatingRepository).deleteComment(commentId);
    }

    // ---------------------------
    // TEST: deleteAllCommentsByProfile
    // ---------------------------
    @Test
    void TestDeleteAllCommentsByProfile() {
        Long profileId = 5L;

        reputationService.deleteAllCommentsByProfile(profileId);

        verify(portRatingRepository).deleteAllCommentsByProfile(profileId);
    }

    // ---------------------------
    // TEST: calculateAverageReputation
    // ---------------------------
    @Test
    void TestCalculateAverageReputation() {
        Long profileId = 1L;

        when(portRatingRepository.calculateAverageReputation(profileId))
                .thenReturn(4.5);

        double result = reputationService.calculateAverageReputation(profileId);

        assertEquals(4.5, result);
        verify(portRatingRepository).calculateAverageReputation(profileId);
    }


    // ---------------------------
    // ⭐ TEST ESPECIAL:
    // calculateWeightedReputation
    // ---------------------------
    @Test
    void TestCalculateWeightedReputation_WithCustomWeights() {
        Long profileId = 1L;

        HashMap<Integer, Double> weights = new HashMap<>();
        weights.put(5, 1.0);
        weights.put(4, 0.5);

        Reputation rep = new Reputation();
        rep.setWeightedScores(weights);

        Profile profile = new Profile();
        profile.setCalification(rep);

        when(portProfileRepository.getProfileById(profileId))
                .thenReturn(profile);

        double result = reputationService.calculateWeightedReputation(profileId);

        double expected = 5 * 1.0 + 4 * 0.5; // 5 + 2 = 7

        assertEquals(expected, result);
        verify(portProfileRepository).getProfileById(profileId);
    }


    @Test
    void TestCalculateWeightedReputation_UsesDefaults_WhenNoWeights() {
        Long profileId = 1L;

        Profile profile = new Profile();
        profile.setCalification(new Reputation()); // weights null

        when(portProfileRepository.getProfileById(profileId))
                .thenReturn(profile);

        double result = reputationService.calculateWeightedReputation(profileId);

        double expected = 5*0.9 + 4*0.8 + 3*0.7 + 2*0.5 + 1*0.5;

        assertEquals(expected, result);
        verify(portProfileRepository).getProfileById(profileId);
    }

}