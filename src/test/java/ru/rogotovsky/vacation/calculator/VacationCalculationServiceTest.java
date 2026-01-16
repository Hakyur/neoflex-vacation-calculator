package ru.rogotovsky.vacation.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.rogotovsky.vacation.calculator.service.VacationCalculationService;
import ru.rogotovsky.vacation.calculator.service.VacationPayableDayService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VacationCalculationServiceTest {

    private static final double AVERAGE_SALARY = 70000.0;

    private VacationPayableDayService vacationPayableDayService;
    private VacationCalculationService calculationService;

    @BeforeEach
    void setUp() {
        vacationPayableDayService = mock(VacationPayableDayService.class);
        calculationService = new VacationCalculationService(vacationPayableDayService);
    }

    @Test
    void testCalculateVacationPayByVacationDays() {
        int vacationDays = 12;

        double expected = expectedVacationPay(AVERAGE_SALARY, vacationDays);
        double actual = calculationService.calculateVacationPay(
                AVERAGE_SALARY, vacationDays, null, null
        );

        assertEquals(expected, actual);
    }

    @Test
    void testCalculateVacationPayByDateRange() {
        LocalDate vacationStartDate = LocalDate.of(2026, 3, 2);
        LocalDate vacationEndDate = LocalDate.of(2026, 3, 15);
        int payableDays = 13;

        when(vacationPayableDayService.countPayableDays(vacationStartDate, vacationEndDate))
                .thenReturn(payableDays);

        double expected = expectedVacationPay(AVERAGE_SALARY, payableDays);
        double actual = calculationService.calculateVacationPay(
                AVERAGE_SALARY, null, vacationStartDate, vacationEndDate
        );

        assertEquals(expected, actual);

        verify(vacationPayableDayService, times(1))
                .countPayableDays(vacationStartDate, vacationEndDate);
    }

    private double expectedVacationPay(double salary, int days) {
        return Math.round((salary / 29.3) * days * 100.0) / 100.0;
    }
}
