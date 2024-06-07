package org.javaacademy.afisha.controller;

import lombok.AllArgsConstructor;
import org.javaacademy.afisha.dto.ReportDto;
import org.javaacademy.afisha.servcie.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
@AllArgsConstructor
public class ReportController {
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<List<ReportDto>> takeReport() {
        return ResponseEntity.ok(reportService.takeReport());
    }
}
