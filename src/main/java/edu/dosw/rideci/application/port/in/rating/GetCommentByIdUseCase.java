package edu.dosw.rideci.application.port.in.rating;

import edu.dosw.rideci.domain.model.Rating;

public interface GetCommentByIdUseCase {
    Rating getCommentById(String commentId);
}
