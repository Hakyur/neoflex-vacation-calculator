package ru.rogotovsky.vacation.calculator.dto;

import ru.rogotovsky.vacation.calculator.validator.ValidVacationCalculation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@ValidVacationCalculation
public class VacationCalculationRequest {

    @NotNull(message = "Average salary must be provided")
    @Positive(message = "Average salary must be positive")
    private Double averageSalary;

    @Positive(message = "Vacation days must be positive")
    private Integer vacationDays;
    private LocalDate vacationStartDay;
    private LocalDate vacationEndDay;

    public double getAverageSalary() {
        return averageSalary;
    }

    public Integer getVacationDays() {
        return vacationDays;
    }

    public LocalDate getVacationStartDay() {
        return vacationStartDay;
    }

    public LocalDate getVacationEndDay() {
        return vacationEndDay;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public void setVacationDays(Integer vacationDays) {
        this.vacationDays = vacationDays;
    }

    public void setVacationStartDay(LocalDate vacationStartDay) {
        this.vacationStartDay = vacationStartDay;
    }

    public void setVacationEndDay(LocalDate vacationEndDay) {
        this.vacationEndDay = vacationEndDay;
    }
}
