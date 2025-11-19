package edu.dosw.rideci.infraestructure.persistence.repository.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.infraestructure.persistence.entity.ProfileDocument;


@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileDocument toDocument(Profile profile);
    Profile toDomain(ProfileDocument profile);
    List<Profile> toListDomain(List<ProfileDocument> profile);
    List<ProfileDocument> toListDocument(List<Profile> profile);

}