package edu.dosw.rideci.application.port.in.rating;

import java.util.List;

public interface ListAllCommentsUseCase {
    List<String> listAllComments(Long profileId);
}
