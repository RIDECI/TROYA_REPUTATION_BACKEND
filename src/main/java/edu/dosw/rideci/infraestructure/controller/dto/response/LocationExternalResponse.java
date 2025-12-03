package edu.dosw.rideci.infraestructure.controller.dto.response;

import lombok.Data;

@Data
public class LocationExternalResponse {
    private double latitude;
    private double longitude;
    private String direction;
}
