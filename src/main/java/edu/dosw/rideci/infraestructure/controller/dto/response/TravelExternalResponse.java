package edu.dosw.rideci.infraestructure.controller.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TravelExternalResponse {

    private String id;
    private Long organizerId;
    private Long driverId;
    private int availableSlots;
    private String status;
    private String travelType;
    private double estimatedCost;
    private LocalDateTime departureDateAndTime;
    private List<Long> passengersId;
    private String conditions;
    private Object origin;
    private Object destiny;
}

