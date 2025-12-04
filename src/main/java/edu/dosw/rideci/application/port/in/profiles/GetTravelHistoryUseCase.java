package edu.dosw.rideci.application.port.in.profiles;

import edu.dosw.rideci.infraestructure.controller.dto.response.TravelExternalResponse;

import java.util.List;

public interface GetTravelHistoryUseCase {
    List<TravelExternalResponse> getUserTravelHistory(Long userId);
}

