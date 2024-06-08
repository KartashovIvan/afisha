package org.javaacademy.afisha.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.javaacademy.afisha.dto.ReportDto;
import org.javaacademy.afisha.servcie.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/report")
@AllArgsConstructor
@Tag(name = "Отчет по мероприятиям" , description = "Операции по получению отчетов по мероприятиям")
public class ReportController {
    private ReportService reportService;

    @GetMapping
    @Operation(summary = "Получение отчета по всем мероприятиям")
    public ResponseEntity<List<ReportDto>> takeReport() {
        return ResponseEntity.ok(reportService.takeReport());
    }
}
