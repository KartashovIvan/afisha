package org.javaacademy.afisha.servcie;

import lombok.AllArgsConstructor;
import org.javaacademy.afisha.dto.ReportDto;
import org.javaacademy.afisha.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {
    private ReportRepository reportRepository;

    public List<ReportDto> takeReport() {
        return reportRepository.takeReport();
    }
}
