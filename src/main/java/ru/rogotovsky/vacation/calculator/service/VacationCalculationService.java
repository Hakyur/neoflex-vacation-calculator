package ru.rogotovsky.vacation.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class VacationCalculationService {

    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;

    public double calculateVacationPay(double averageSalary, int vacationDays) {
        double averageDailySalary = averageSalary / AVERAGE_DAYS_IN_MONTH;
        return round(averageDailySalary * vacationDays);
    }

    public double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
