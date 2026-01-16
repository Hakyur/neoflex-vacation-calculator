package ru.rogotovsky.vacation.calculator.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response containing calculated vacation pay")
public class VacationCalculationResponse {

    @Schema(
            description = "Calculated vacation pay amount",
            example = "45942.27"
    )
    private final double vacationPay;

    public VacationCalculationResponse(double vacationPay) {
        this.vacationPay = vacationPay;
    }

    public double getVacationPay() {
        return vacationPay;
    }
}
