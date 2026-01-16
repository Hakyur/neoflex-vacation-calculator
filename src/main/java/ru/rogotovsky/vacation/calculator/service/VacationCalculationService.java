package ru.rogotovsky.vacation.calculator.service;

import org.springframework.stereotype.Service;
import ru.rogotovsky.vacation.calculator.exception.InvalidVacationRequestException;

import java.time.LocalDate;

@Service
public class VacationCalculationService {

    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;

    private final VacationPayableDayService vacationPayableDayService;

    public VacationCalculationService(VacationPayableDayService vacationPayableDayService) {
        this.vacationPayableDayService = vacationPayableDayService;
    }

    public double calculateVacationPay(
            double averageSalary,
            Integer vacationDays,
            LocalDate vacationStartDay,
            LocalDate vacationEndDay
    ) {
        int payableDays;

        if (vacationDays != null) {
            payableDays = vacationDays;
        } else {
            payableDays = vacationPayableDayService.countPayableDays(vacationStartDay, vacationEndDay);
        }

        double averageDailySalary = averageSalary / AVERAGE_DAYS_IN_MONTH;
        return round(averageDailySalary * payableDays);
    }

    public double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
