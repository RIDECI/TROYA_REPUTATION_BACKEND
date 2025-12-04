package edu.dosw.rideci.infraestructure.client;

import edu.dosw.rideci.infraestructure.controller.dto.response.TravelExternalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TravelClient {

    private final RestTemplate restTemplate;

    private final String BASE_URL = "https://nemesistravelmanagementbackend-production.up.railway.app/travels";

    public List<TravelExternalResponse> getTravelsByDriver(Long driverId) {
        String url = BASE_URL + "/driver/" + driverId;
        TravelExternalResponse[] response = restTemplate.getForObject(url, TravelExternalResponse[].class);
        return Arrays.asList(response != null ? response : new TravelExternalResponse[0]);
    }

    public List<TravelExternalResponse> getTravelsByOrganizer(Long organizerId) {
        String url = BASE_URL + "/organizer/" + organizerId;
        TravelExternalResponse[] response = restTemplate.getForObject(url, TravelExternalResponse[].class);
        return Arrays.asList(response != null ? response : new TravelExternalResponse[0]);
    }

    public List<TravelExternalResponse> getTravelsByPassenger(Long passengerId) {
        String url = BASE_URL + "/passenger/" + passengerId;
        TravelExternalResponse[] response = restTemplate.getForObject(url, TravelExternalResponse[].class);
        return Arrays.asList(response != null ? response : new TravelExternalResponse[0]);
    }
}

