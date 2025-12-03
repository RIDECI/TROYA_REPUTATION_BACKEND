package edu.dosw.rideci.application.port.in.profiles;

import edu.dosw.rideci.infraestructure.controller.dto.response.ReportExternalResponse;
import java.util.List;

public interface GetUserReportsUseCase {
    List<ReportExternalResponse> getReportsByUser(Long userId);
}
