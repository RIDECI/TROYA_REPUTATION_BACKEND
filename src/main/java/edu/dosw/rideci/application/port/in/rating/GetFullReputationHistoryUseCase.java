package edu.dosw.rideci.application.port.in.rating;

import edu.dosw.rideci.domain.model.Rating;
import java.util.List;

public interface GetFullReputationHistoryUseCase {
    List<Rating> getReputationHistory(Long profileId);
}
