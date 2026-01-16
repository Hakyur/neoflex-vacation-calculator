package ru.rogotovsky.vacation.calculator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.rogotovsky.vacation.calculator.dto.VacationCalculationRequest;
import ru.rogotovsky.vacation.calculator.dto.VacationCalculationResponse;
import ru.rogotovsky.vacation.calculator.service.VacationCalculationService;

import javax.validation.Valid;

@RestController
public class VacationCalculationController {

    private final VacationCalculationService calculationService;

    public VacationCalculationController(VacationCalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping("/calculacte")
    public ResponseEntity<?> calculate(@Valid @RequestBody VacationCalculationRequest request) {
        return ResponseEntity.ok(new VacationCalculationResponse(
                calculationService.calculateVacationPay(
                        request.getAverageSalary(),
                        request.getVacationDays(),
                        request.getVacationStartDay(),
                        request.getVacationEndDay()
                )));
    }
}
