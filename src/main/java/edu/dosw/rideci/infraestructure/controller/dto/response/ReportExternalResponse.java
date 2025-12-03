package edu.dosw.rideci.infraestructure.controller.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReportExternalResponse {
    private String id;
    private String type;       // Recibido como string desde el módulo 5
    private Long userId;
    private Long targetId;
    private Long tripId;
    private LocationExternalResponse location; // Tu propia clase DTO
    private String description;
    private LocalDateTime createdAt;
}
