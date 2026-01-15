package ru.rogotovsky.vacation.calculator.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VacationCalculationService {

    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;

    public double calculateVacationPay(
            double averageSalary,
            Integer vacationDays,
            LocalDate vacationStartDay,
            LocalDate vacationEndDay
    ) {
        double averageDailySalary = averageSalary / AVERAGE_DAYS_IN_MONTH;
        return round(averageDailySalary * vacationDays);
    }

    public double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
