package ru.rogotovsky.vacation.calculator.dto;

public class VacationCalculationResponse {
    private final double vacationPay;

    public VacationCalculationResponse(double vacationPay) {
        this.vacationPay = vacationPay;
    }

    public double getVacationPay() {
        return vacationPay;
    }
}
