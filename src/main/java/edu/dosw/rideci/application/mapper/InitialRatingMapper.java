package edu.dosw.rideci.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Rating;
import edu.dosw.rideci.infraestructure.controller.dto.request.RatingRequestDTO;
import edu.dosw.rideci.infraestructure.controller.dto.response.BadgeResponse;
import edu.dosw.rideci.infraestructure.controller.dto.response.RatingResponseDTO;

@Mapper(componentModel = "spring")
public interface InitialRatingMapper {
    
    RatingResponseDTO toResponse(Rating rating);
    Rating toDomain(RatingRequestDTO ratingRequest);
    List<BadgeResponse> toBadgeResponse(List<Badge> badge);
    List<RatingResponseDTO> toListResponse(List<Rating> rating);
    List<Rating> toListDomain(List<RatingRequestDTO> rating);
    
}
