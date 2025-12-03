package edu.dosw.rideci.infraestructure.client;

import edu.dosw.rideci.infraestructure.controller.dto.response.ReportExternalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReportClient {

    private final RestTemplate restTemplate;

    // URL del módulo 5
    private final String BASE_URL = "https://hadescommunicationsecuritybackend-development.up.railway.app";

    public List<ReportExternalResponse> getReportsByUser(Long userId) {

        String url = BASE_URL + "/reports/user/" + userId;

        ReportExternalResponse[] response =
                restTemplate.getForObject(url, ReportExternalResponse[].class);

        return Arrays.asList(response);
    }
}

