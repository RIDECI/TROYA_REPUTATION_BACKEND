package edu.dosw.rideci.application.port.in.rating;

import edu.dosw.rideci.domain.model.Rating;
import java.util.List;

public interface GetTripReputationDetailUseCase {
    List<Rating> getRatingsForTripId(Long tripId);
}
