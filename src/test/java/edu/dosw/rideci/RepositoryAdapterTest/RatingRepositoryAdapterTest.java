package edu.dosw.rideci.RepositoryAdapterTest;

import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Rating;
import edu.dosw.rideci.infraestructure.persistence.entity.BadgeDocument;
import edu.dosw.rideci.infraestructure.persistence.entity.ProfileDocument;
import edu.dosw.rideci.infraestructure.persistence.entity.RatingDocument;
import edu.dosw.rideci.infraestructure.persistence.entity.ReputationDocument;
import edu.dosw.rideci.infraestructure.persistence.repository.profile.ProfileRepository;
import edu.dosw.rideci.infraestructure.persistence.repository.rating.RatingRepository;
import edu.dosw.rideci.infraestructure.persistence.repository.rating.RatingRepositoryAdapter;
import edu.dosw.rideci.infraestructure.persistence.repository.mapper.RatingMapper;
import edu.dosw.rideci.exceptions.RatingNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RatingRepositoryAdapterTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private RatingMapper ratingMapper;

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private RatingRepositoryAdapter adapter;

    @BeforeEach
    void setup() {
    }

    @Test
    void calculateAverageReputation_returns0WhenNoRatings() {
        Long profileId = 1L;
        when(ratingRepository.findAllByTargetId(profileId)).thenReturn(Collections.emptyList());

        double avg = adapter.calculateAverageReputation(profileId);

        assertEquals(0.0, avg);
    }

    @Test
    void calculateAverageReputationUsesDefaultWeightsWhenNone() {
        Long profileId = 2L;
        RatingDocument r1 = RatingDocument.builder().id(1L).score(5).build();
        RatingDocument r2 = RatingDocument.builder().id(2L).score(4).build();
        when(ratingRepository.findAllByTargetId(profileId)).thenReturn(List.of(r1, r2));

        when(profileRepository.findById(profileId)).thenReturn(Optional.of(ProfileDocument.builder().id(profileId).build()));

        double avg = adapter.calculateAverageReputation(profileId);

        assertEquals(4.5, avg, 1e-9);
    }

    @Test
    void calculateAverageReputation_usesProfileWeightsWhenPresent() {
        Long profileId = 3L;
        RatingDocument r1 = RatingDocument.builder().id(1L).score(5).build();
        RatingDocument r2 = RatingDocument.builder().id(2L).score(4).build();
        when(ratingRepository.findAllByTargetId(profileId)).thenReturn(List.of(r1, r2));

        HashMap<Integer, Double> weights = new HashMap<>();
        weights.put(5, 2.0); 
        weights.put(4, 1.0);

        ReputationDocument rd = ReputationDocument.builder().weightedScores(weights).build();
        ProfileDocument pd = ProfileDocument.builder().id(profileId).calification(rd).build();

        when(profileRepository.findById(profileId)).thenReturn(Optional.of(pd));

        double avg = adapter.calculateAverageReputation(profileId);

        assertEquals(7.0, avg, 1e-9);
    }

    @Test
    void getRatingById_whenNotFound_throws() {
        Long id = 10L;
        when(ratingRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RatingNotFoundException.class, () -> adapter.getRatingById(id));
    }

    @Test
    void getRatingById_whenFound_mapsAndReturns() {
        Long id = 11L;
        RatingDocument doc = RatingDocument.builder().id(id).score(3).build();
        Rating domain = new Rating();
        when(ratingRepository.findById(id)).thenReturn(Optional.of(doc));
        when(ratingMapper.toDomain(doc)).thenReturn(domain);

        Rating res = adapter.getRatingById(id);
        assertSame(domain, res);
    }

    @Test
    void getRatingsByProfile_delegatesToMapper() {
        Long profileId = 20L;
        RatingDocument d1 = RatingDocument.builder().id(1L).score(5).build();
        RatingDocument d2 = RatingDocument.builder().id(2L).score(4).build();
        when(ratingRepository.findAllByTargetId(profileId)).thenReturn(List.of(d1, d2));

        Rating r1 = new Rating();
        Rating r2 = new Rating();
        when(ratingMapper.toListDomain(List.of(d1, d2))).thenReturn(List.of(r1, r2));

        List<Rating> out = adapter.getRatingsByProfile(profileId);
        assertEquals(2, out.size());
        verify(ratingMapper).toListDomain(List.of(d1, d2));
    }

    @Test
    void getCommentById_missingComment_throws() {
        Long id = 30L;
        RatingDocument doc = RatingDocument.builder().id(id).comment(null).build();
        when(ratingRepository.findById(id)).thenReturn(Optional.of(doc));

        assertThrows(RatingNotFoundException.class, () -> adapter.getCommentById(id));
    }
    

    @Test
    void getRatingsForTripId_delegates() {
        Long tripId = 60L;
        RatingDocument d1 = RatingDocument.builder().id(1L).tripId(tripId).build();
        when(ratingRepository.findAllByTripId(tripId)).thenReturn(List.of(d1));
        when(ratingMapper.toListDomain(List.of(d1))).thenReturn(List.of(new Rating()));

        List<Rating> out = adapter.getRatingsForTripId(tripId);
        assertEquals(1, out.size());
        verify(ratingRepository).findAllByTripId(tripId);
    }

    @Test
    void getBadgesForUser_returnsEmptyWhenNoProfile() {
        Long profileId = 70L;
        when(profileRepository.findById(profileId)).thenReturn(Optional.empty());

        List<Badge> out = adapter.getBadgesForUser(profileId);
        assertTrue(out.isEmpty());
    }

    @Test
    void getBadgesForUser_mapsBadges() {
        Long profileId = 71L;
        BadgeDocument b1 = new BadgeDocument();
        b1.setName("gold"); b1.setPathImageColor("/img/g"); b1.setDescription("desc"); b1.setActive(true);
        ProfileDocument pd = ProfileDocument.builder().id(profileId).badges(List.of(b1)).build();
        when(profileRepository.findById(profileId)).thenReturn(Optional.of(pd));

        List<Badge> out = adapter.getBadgesForUser(profileId);
        assertEquals(1, out.size());
        assertEquals("gold", out.get(0).getName());
        assertEquals("/img/g", out.get(0).getPathImageColor());
    }

    @Test
    void listAllComments_returnsOnlyNonEmpty() {
        Long profileId = 80L;
        RatingDocument d1 = RatingDocument.builder().id(1L).comment("a").build();
        RatingDocument d2 = RatingDocument.builder().id(2L).comment("").build();
        when(ratingRepository.findAllByTargetId(profileId)).thenReturn(List.of(d1, d2));

        List<String> out = adapter.listAllComments(profileId);
        assertEquals(1, out.size());
        assertEquals("a", out.get(0));
    }

    @Test
    void deleteComment_and_deleteAllCommentsByProfile_callRepository() {
        adapter.deleteComment(101L);
        verify(ratingRepository).deleteById(101L);

        adapter.deleteAllCommentsByProfile(202L);
        verify(ratingRepository).deleteByTargetId(202L);
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
