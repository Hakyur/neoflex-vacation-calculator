package ru.rogotovsky.vacation.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.rogotovsky.vacation.calculator.exception.InvalidVacationRequestException;
import ru.rogotovsky.vacation.calculator.service.VacationCalculationService;
import ru.rogotovsky.vacation.calculator.service.VacationPayableDayService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void testCalculateByVacationDays() {
        int vacationDays = 12;

        double expected = Math.round((AVERAGE_SALARY / 29.3) * vacationDays * 100.0) / 100.0;
        double actual = calculationService.calculateVacationPay(AVERAGE_SALARY, vacationDays, null, null);

        assertEquals(expected, actual);
    }

    @Test
    void testCalculatedByDateRange() {
        LocalDate vacationStartDay = LocalDate.of(2026, 3, 2);
        LocalDate vacationEndDay = LocalDate.of(2026, 3, 15);

        when(vacationPayableDayService.countPayableDays(vacationStartDay, vacationEndDay)).thenReturn(13);

        double expected = Math.round((AVERAGE_SALARY / 29.3) * 13 * 100.0) / 100.0;
        double actual = calculationService.calculateVacationPay(
                AVERAGE_SALARY, null, vacationStartDay, vacationEndDay
        );

        assertEquals(expected, actual);

        verify(vacationPayableDayService, times(1))
                .countPayableDays(vacationStartDay, vacationEndDay);
    }

    @Test
    void testExceptionIfStartAfterEnd() {
        LocalDate vacationStartDay = LocalDate.of(2026, 1, 10);
        LocalDate vacationEndDay = LocalDate.of(2026, 1, 9);

        Exception exception = assertThrows(InvalidVacationRequestException.class,
                () -> calculationService.calculateVacationPay(
                        AVERAGE_SALARY, null, vacationStartDay, vacationEndDay
                ));
        assertEquals("Start date must be before end date", exception.getMessage());
    }

    @Test
    void testExceptionIfNoDaysOrDatesProvided() {
        Exception exception = assertThrows(InvalidVacationRequestException.class,
                () -> calculationService.calculateVacationPay(
                        AVERAGE_SALARY, null, null, null
                ));
        assertEquals("Vacation days or date range must be provided", exception.getMessage());
    }
}
