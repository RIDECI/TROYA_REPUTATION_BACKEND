package edu.dosw.rideci.infraestructure.persistence.repository.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import edu.dosw.rideci.domain.model.Rating;
import edu.dosw.rideci.infraestructure.persistence.entity.RatingDocument;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    RatingDocument toDocument(Rating rating);
    Rating toDomain(RatingDocument rating);
    List<Rating> toListDomain(List<RatingDocument> rating);
    List<RatingDocument> toListDocument(List<Rating> rating);
}