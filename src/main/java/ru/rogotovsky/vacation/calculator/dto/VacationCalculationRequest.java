package ru.rogotovsky.vacation.calculator.dto;

import java.time.LocalDate;

public class VacationCalculationRequest {

    private double averageSalary;
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
