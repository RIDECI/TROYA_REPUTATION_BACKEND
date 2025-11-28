package edu.dosw.rideci.RepositoryAdapterTest;

import edu.dosw.rideci.application.service.RatingService;
import edu.dosw.rideci.application.port.out.PortRatingRepository;
import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Rating;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RatingRepositoryAdapterTest {

    @Mock
    private PortRatingRepository portRatingRepository;

    @InjectMocks
    private RatingService ratingService;

    @Test
    void createRating_delegatesToRepository_andReturnsSaved() {
        Rating in = new Rating();
        Rating saved = new Rating();

        when(portRatingRepository.createRating(in)).thenReturn(saved);

        Rating out = ratingService.createRating(in);

        assertSame(saved, out);
        verify(portRatingRepository).createRating(in);
    }

    @Test
    void listAllComments_delegates() {
        Long profileId = 1L;
        when(portRatingRepository.listAllComments(profileId)).thenReturn(List.of("a","b"));

        var out = ratingService.listAllComments(profileId);

        assertEquals(2, out.size());
        verify(portRatingRepository).listAllComments(profileId);
    }

    @Test
    void getBadgesForUser_delegates() {
        Long id = 2L;
        Badge b = new Badge(); b.setName("gold");
        when(portRatingRepository.getBadgesForUser(id)).thenReturn(List.of(b));

        var out = ratingService.getBadgesForUser(id);

        assertEquals(1, out.size());
        assertEquals("gold", out.get(0).getName());
        verify(portRatingRepository).getBadgesForUser(id);
    }

    @Test
    void getRatingsForTripId_delegates() {
        Long tripId = 3L;
        Rating r = new Rating();
        when(portRatingRepository.getRatingsForTripId(tripId)).thenReturn(List.of(r));

        var out = ratingService.getRatingsForTripId(tripId);

        assertEquals(1, out.size());
        verify(portRatingRepository).getRatingsForTripId(tripId);
    }

    @Test
    void getRatingById_delegates() {
        Long id = 4L;
        Rating r = new Rating();
        when(portRatingRepository.getRatingById(id)).thenReturn(r);

        var out = ratingService.getRatingById(id);

        assertSame(r, out);
        verify(portRatingRepository).getRatingById(id);
    }

    @Test
    void getReputationHistory_delegates() {
        Long id = 5L;
        Rating r = new Rating();
        when(portRatingRepository.getReputationHistory(id)).thenReturn(List.of(r));

        var out = ratingService.getReputationHistory(id);

        assertEquals(1, out.size());
        verify(portRatingRepository).getReputationHistory(id);
    }

    @Test
    void getCommentById_delegates() {
        Long id = 6L;
        Rating r = new Rating();
        when(portRatingRepository.getCommentById(id)).thenReturn(r);

        var out = ratingService.getCommentById(id);

        assertSame(r, out);
        verify(portRatingRepository).getCommentById(id);
    }

    @Test
    void getAllCommentsByProfile_delegates() {
        Long id = 7L;
        Rating r = new Rating();
        when(portRatingRepository.getCommentsByProfile(id)).thenReturn(List.of(r));

        var out = ratingService.getAllCommentsByProfile(id);

        assertEquals(1, out.size());
        verify(portRatingRepository).getCommentsByProfile(id);
    }

    @Test
    void deleteComment_and_deleteAllCommentsByProfile_delegates() {
        ratingService.deleteComment(10L);
        verify(portRatingRepository).deleteComment(10L);

        ratingService.deleteAllCommentsByProfile(20L);
        verify(portRatingRepository).deleteAllCommentsByProfile(20L);
    }

    @Test
    void calculateAverageReputation_delegates() {
        Long id = 9L;
        when(portRatingRepository.calculateAverageReputation(id)).thenReturn(3.14);

        double out = ratingService.calculateAverageReputation(id);

        assertEquals(3.14, out);
        verify(portRatingRepository).calculateAverageReputation(id);
    }

    @Test
    void calculateTripRating_returns0WhenNoRatings() {
        Long tripId = 1000L;
        when(ratingRepository.findAllByTripId(tripId)).thenReturn(Collections.emptyList());

        double rating = adapter.calculateTripRating(tripId);

        assertEquals(0.0, rating, 1e-9);
        verify(ratingRepository).findAllByTripId(tripId);
    }

    @Test
    void calculateTripRating_withDefaultWeights() {
        Long tripId = 1001L;
        RatingDocument r1 = RatingDocument.builder().id(1L).tripId(tripId).score(5).build();
        RatingDocument r2 = RatingDocument.builder().id(2L).tripId(tripId).score(4).build();
        RatingDocument r3 = RatingDocument.builder().id(3L).tripId(tripId).score(3).build();

        when(ratingRepository.findAllByTripId(tripId)).thenReturn(List.of(r1, r2, r3));

        double rating = adapter.calculateTripRating(tripId);
        // Cálculo: (5*1.0 + 4*1.0 + 3*0.7) / 3 = (5 + 4 + 2.1) / 3 = 11.1 / 3 = 3.7
        assertEquals(3.7, rating, 1e-9);
        verify(ratingRepository).findAllByTripId(tripId);
    }

    @Test
    void calculateTripRating_withLowScores() {
        Long tripId = 1002L;
        RatingDocument r1 = RatingDocument.builder().id(1L).tripId(tripId).score(2).build();
        RatingDocument r2 = RatingDocument.builder().id(2L).tripId(tripId).score(1).build();

        when(ratingRepository.findAllByTripId(tripId)).thenReturn(List.of(r1, r2));

        double rating = adapter.calculateTripRating(tripId);
        // Cálculo: (2*0.5 + 1*0.5) / 2 = (1.0 + 0.5) / 2 = 1.5 / 2 = 0.75
        assertEquals(0.75, rating, 1e-9);
        verify(ratingRepository).findAllByTripId(tripId);
    }

    @Test
    void calculateTripRating_withMixedScores() {
        Long tripId = 1003L;
        RatingDocument r1 = RatingDocument.builder().id(1L).tripId(tripId).score(5).build();
        RatingDocument r2 = RatingDocument.builder().id(2L).tripId(tripId).score(4).build();
        RatingDocument r3 = RatingDocument.builder().id(3L).tripId(tripId).score(3).build();
        RatingDocument r4 = RatingDocument.builder().id(4L).tripId(tripId).score(2).build();
        RatingDocument r5 = RatingDocument.builder().id(5L).tripId(tripId).score(1).build();

        when(ratingRepository.findAllByTripId(tripId)).thenReturn(List.of(r1, r2, r3, r4, r5));

        double rating = adapter.calculateTripRating(tripId);

        // Cálculo: (5*1.0 + 4*1.0 + 3*0.7 + 2*0.5 + 1*0.5) / 5
        // = (5 + 4 + 2.1 + 1.0 + 0.5) / 5 = 12.6 / 5 = 2.52
        assertEquals(2.52, rating, 1e-9);
        verify(ratingRepository).findAllByTripId(tripId);
    }

    @Test
    void calculateTripRating_filtersNullScores() {
        Long tripId = 1004L;
        RatingDocument r1 = RatingDocument.builder().id(1L).tripId(tripId).score(5).build();
        RatingDocument r2 = RatingDocument.builder().id(2L).tripId(tripId).score(null).build();
        RatingDocument r3 = RatingDocument.builder().id(3L).tripId(tripId).score(4).build();

        when(ratingRepository.findAllByTripId(tripId)).thenReturn(List.of(r1, r2, r3));

        double rating = adapter.calculateTripRating(tripId);
        // Debe ignorar el null y calcular solo con 5 y 4
        // (5*1.0 + 4*1.0) / 2 = 9 / 2 = 4.5
        assertEquals(4.5, rating, 1e-9);
        verify(ratingRepository).findAllByTripId(tripId);
    }

    @Test
    void calculateSimpleTripRating_returns0WhenNoRatings() {
        Long tripId = 2000L;
        when(ratingRepository.findAllByTripId(tripId)).thenReturn(Collections.emptyList());

        double rating = adapter.calculateSimpleTripRating(tripId);

        assertEquals(0.0, rating, 1e-9);
        verify(ratingRepository).findAllByTripId(tripId);
    }

    @Test
    void calculateSimpleTripRating_calculatesArithmeticAverage() {
        Long tripId = 2001L;
        RatingDocument r1 = RatingDocument.builder().id(1L).tripId(tripId).score(5).build();
        RatingDocument r2 = RatingDocument.builder().id(2L).tripId(tripId).score(4).build();
        RatingDocument r3 = RatingDocument.builder().id(3L).tripId(tripId).score(3).build();

        when(ratingRepository.findAllByTripId(tripId)).thenReturn(List.of(r1, r2, r3));

        double rating = adapter.calculateSimpleTripRating(tripId);

        // Promedio simple: (5 + 4 + 3) / 3 = 12 / 3 = 4.0
        assertEquals(4.0, rating, 1e-9);
        verify(ratingRepository).findAllByTripId(tripId);
    }

    @Test
    void calculateSimpleTripRating_withLowScores() {
        Long tripId = 2002L;
        RatingDocument r1 = RatingDocument.builder().id(1L).tripId(tripId).score(2).build();
        RatingDocument r2 = RatingDocument.builder().id(2L).tripId(tripId).score(1).build();

        when(ratingRepository.findAllByTripId(tripId)).thenReturn(List.of(r1, r2));

        double rating = adapter.calculateSimpleTripRating(tripId);

        // Promedio simple: (2 + 1) / 2 = 3 / 2 = 1.5
        assertEquals(1.5, rating, 1e-9);
        verify(ratingRepository).findAllByTripId(tripId);
    }

    @Test
    void calculateSimpleTripRating_filtersNullScores() {
        Long tripId = 2003L;
        RatingDocument r1 = RatingDocument.builder().id(1L).tripId(tripId).score(5).build();
        RatingDocument r2 = RatingDocument.builder().id(2L).tripId(tripId).score(null).build();
        RatingDocument r3 = RatingDocument.builder().id(3L).tripId(tripId).score(3).build();

        when(ratingRepository.findAllByTripId(tripId)).thenReturn(List.of(r1, r2, r3));

        double rating = adapter.calculateSimpleTripRating(tripId);

        // Debe ignorar el null: (5 + 3) / 2 = 8 / 2 = 4.0
        assertEquals(4.0, rating, 1e-9);
        verify(ratingRepository).findAllByTripId(tripId);
    }

    @Test
    void calculateSimpleTripRating_withAllPerfectScores() {
        Long tripId = 2004L;
        RatingDocument r1 = RatingDocument.builder().id(1L).tripId(tripId).score(5).build();
        RatingDocument r2 = RatingDocument.builder().id(2L).tripId(tripId).score(5).build();
        RatingDocument r3 = RatingDocument.builder().id(3L).tripId(tripId).score(5).build();

        when(ratingRepository.findAllByTripId(tripId)).thenReturn(List.of(r1, r2, r3));

        double rating = adapter.calculateSimpleTripRating(tripId);

        assertEquals(5.0, rating, 1e-9);
        verify(ratingRepository).findAllByTripId(tripId);
    }

    @Test
    void compareWeightedVsSimpleRating_showsDifference() {
        Long tripId = 3000L;
        RatingDocument r1 = RatingDocument.builder().id(1L).tripId(tripId).score(5).build();
        RatingDocument r2 = RatingDocument.builder().id(2L).tripId(tripId).score(4).build();
        RatingDocument r3 = RatingDocument.builder().id(3L).tripId(tripId).score(2).build();

        List<RatingDocument> ratings = List.of(r1, r2, r3);
        when(ratingRepository.findAllByTripId(tripId)).thenReturn(ratings);

        double weightedRating = adapter.calculateTripRating(tripId);

        when(ratingRepository.findAllByTripId(tripId)).thenReturn(ratings);
        double simpleRating = adapter.calculateSimpleTripRating(tripId);

        // Weighted: (5*1.0 + 4*1.0 + 2*0.5) / 3 = (5 + 4 + 1) / 3 = 10/3 = 3.333...
        assertEquals(3.333, weightedRating, 0.001);

        // Simple: (5 + 4 + 2) / 3 = 11 / 3 = 3.666...
        assertEquals(3.666, simpleRating, 0.001);

        assertTrue(weightedRating < simpleRating,
                "El rating ponderado debe ser menor que el simple cuando hay calificaciones bajas");
    }
}
