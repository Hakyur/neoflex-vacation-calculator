package ru.rogotovsky.vacation.calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rogotovsky.vacation.calculator.dto.VacationCalculationResponse;

@RestController
public class VacationCalculationController {

    @GetMapping("/calculacte")
    public VacationCalculationResponse calculate() {
        return new VacationCalculationResponse();
    }
}
