package edu.dosw.rideci.application.port.in.rating;

public interface DeleteCommentsAdminUseCase {
    void deleteComment(String commentId); 
    void deleteAllCommentsByProfile(Long userId); 
}
