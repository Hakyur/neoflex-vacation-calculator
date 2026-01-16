package ru.rogotovsky.vacation.calculator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.rogotovsky.vacation.calculator.validator.ValidVacationCalculation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Schema(
        description = "Request for vacation pay calculation. " +
                "Either vacationDays or both vacationStartDay and vacationEndDay must be provided"
)
@ValidVacationCalculation
public class VacationCalculationRequest {

    @Schema(
            description = "Average monthly salary",
            example = "100000.00",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Average salary must be provided")
    @Positive(message = "Average salary must be positive")
    private Double averageSalary;

    @Schema(
            description = "Number of vacation days. Alternative to date range",
            example = "10"
    )
    @Positive(message = "Vacation days must be positive")
    private Integer vacationDays;

    @Schema(
            description = "Vacation start date",
            example = "2026-01-12"
    )
    private LocalDate vacationStartDay;

    @Schema(
            description = "Vacation end date",
            example = "2026-01-22"
    )
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
