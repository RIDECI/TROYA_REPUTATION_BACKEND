package edu.dosw.rideci.application.port.in.rating;

public interface DeleteCommentsAdminUseCase {
    void deleteComment(Long commentId); 
    void deleteAllCommentsByProfile(Long profileId); 
}
