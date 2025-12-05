package edu.dosw.rideci.application.port.in.rating;

import java.util.List;

import edu.dosw.rideci.domain.model.Rating;

public interface GetAllCommentsUseCase {
    List<Rating> getAllCommentsByProfile(Long userId);
    
}
