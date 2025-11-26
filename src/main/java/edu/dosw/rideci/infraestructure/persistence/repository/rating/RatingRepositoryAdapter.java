package edu.dosw.rideci.infraestructure.persistence.repository.rating;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import edu.dosw.rideci.application.port.out.PortRatingRepository;
import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Rating;
import edu.dosw.rideci.exceptions.RatingNotFoundException;
import edu.dosw.rideci.infraestructure.persistence.entity.ReputationDocument;
import edu.dosw.rideci.infraestructure.persistence.entity.ProfileDocument;
import edu.dosw.rideci.infraestructure.persistence.entity.RatingDocument;
import edu.dosw.rideci.infraestructure.persistence.entity.BadgeDocument;
import edu.dosw.rideci.infraestructure.persistence.repository.mapper.RatingMapper;
import edu.dosw.rideci.infraestructure.persistence.repository.profile.ProfileRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RatingRepositoryAdapter implements PortRatingRepository {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final ProfileRepository profileRepository;


    //Revisar
    @Override
    public double calculateAverageReputation(Long profileId) {
        List<RatingDocument> docs = ratingRepository.findAllByTargetId(profileId);

        if (docs == null || docs.isEmpty()) {
            return 0.0;
        }
        
        // Obtener pesos desde el perfil 
        Optional<ProfileDocument> profileOpt = profileRepository.findById(profileId);
        HashMap<Integer, Double> weights = null;
        if (profileOpt.isPresent()) {
            ProfileDocument pd = profileOpt.get();
            ReputationDocument rd = pd.getCalification();
            if (rd != null) {
                weights = rd.getWeightedScores();
            }
        }

        // Definir mapa por defecto si no existen pesos en el perfil
        HashMap<Integer, Double> defaults = new HashMap<>();
        defaults.put(5, 1.0);
        defaults.put(4, 1.0);
        defaults.put(3, 0.7);
        defaults.put(2, 0.5);
        defaults.put(1, 0.5);

        HashMap<Integer, Double> effectiveWeights = (weights == null || weights.isEmpty()) ? defaults : weights;

        // calcular con pesos = score * weight
        //promedio?
        return docs.stream()
                .map(RatingDocument::getScore)
                .filter(s -> s != null)
                .mapToDouble(s -> s * effectiveWeights.getOrDefault(s, 1.0))
                .average()
                .orElse(0.0);
    }


    @Override
    public Rating getRatingById(Long ratingId) {
        RatingDocument ratingDocument = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new RatingNotFoundException("Rating not found"));
        return ratingMapper.toDomain(ratingDocument);
    }

    @Override
    public List<Rating> getRatingsByProfile(Long profileId) {
        List<RatingDocument> docs = ratingRepository.findAllByTargetId(profileId);
        return ratingMapper.toListDomain(docs);
    }

    //Revisar
    @Override
    public Rating getCommentById(Long commentId) {
        RatingDocument ratingDocument = ratingRepository.findById(commentId)
                .orElseThrow(() -> new RatingNotFoundException("Comment not found"));

        String comment = ratingDocument.getComment();
        if (comment == null || comment.trim().isEmpty()) {
            throw new RatingNotFoundException("Comment not found");
        }

        return ratingMapper.toDomain(ratingDocument);
    }

    //Revisar
    @Override
    public List<Rating> getReputationHistory(Long profileId) {
        List<RatingDocument> docs = ratingRepository.findAllByTargetId(profileId);

        List<RatingDocument> sorted = docs.stream()
                .sorted((a, b) -> {
                    if (a.getDate() == null && b.getDate() == null) return 0;
                    if (a.getDate() == null) return 1;
                    if (b.getDate() == null) return -1;
                    return b.getDate().compareTo(a.getDate());
                })
                .collect(Collectors.toList());

        return ratingMapper.toListDomain(sorted);
    }

    //Revisar
    @Override
    public List<Rating> getCommentsByProfile(Long profileId) {
        List<RatingDocument> docs = ratingRepository.findAllByTargetId(profileId);

        List<RatingDocument> withComments = docs.stream()
                .filter(d -> d.getComment() != null && !d.getComment().trim().isEmpty())
                .collect(Collectors.toList());

        return ratingMapper.toListDomain(withComments);
    }

    @Override
    public List<Rating> getRatingsForTripId(Long tripId) {
        List<RatingDocument> docs = ratingRepository.findAllByTripId(tripId);
        return ratingMapper.toListDomain(docs);
    }

    //Revisar
    @Override
    public List<Badge> getBadgesForUser(Long profileId) {
        Optional<ProfileDocument> profileOpt = profileRepository.findById(profileId);
        if (profileOpt.isEmpty()) {
            return Collections.emptyList();
        }

        ProfileDocument profile = profileOpt.get();
        List<BadgeDocument> badgeDocs = profile.getBadges();
        if (badgeDocs == null || badgeDocs.isEmpty()) {
            return Collections.emptyList();
        }

        return badgeDocs.stream()
                .map(bd -> Badge.builder()
                    .name(bd.getName())
                    .pathImageBlackAndWhite(bd.getPathImageBlackAndWhite())
                    .pathImageColor(bd.getPathImageColor())
                    .description(bd.getDescription())
                    .isActive(bd.isActive())
                    .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listAllComments(Long profileId) {
        List<RatingDocument> docs = ratingRepository.findAllByTargetId(profileId);

        return docs.stream()
                .map(RatingDocument::getComment)       
                .filter(comment -> comment != null && !comment.isEmpty()) 
                .collect(Collectors.toList());

                
    }

    @Override
    public void deleteComment(Long commentId) {
        ratingRepository.deleteById(commentId);
    }

    @Override
    public void deleteAllCommentsByProfile(Long profileId) {
        ratingRepository.deleteByTargetId(profileId);
    }


    @Override
    public Rating createRating(Rating rating) {
        return rating;
        
    }
    
}
