package edu.dosw.rideci.application.port.in.rating;

public interface CalculateTripRatingUseCase {
    double calculateTripRating(Long tripId);
    double calculateSimpleTripRating(Long tripId);
}