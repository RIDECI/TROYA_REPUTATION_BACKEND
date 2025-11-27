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
}
