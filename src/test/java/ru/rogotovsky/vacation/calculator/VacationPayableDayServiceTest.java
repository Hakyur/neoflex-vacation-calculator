package ru.rogotovsky.vacation.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.rogotovsky.vacation.calculator.calendar.HolidayCalendar;
import ru.rogotovsky.vacation.calculator.service.VacationPayableDayService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VacationPayableDayServiceTest {

    private HolidayCalendar holidayCalendar;
    private VacationPayableDayService service;

    @BeforeEach
    void setUp() {
        holidayCalendar = mock(HolidayCalendar.class);
        service = new VacationPayableDayService(holidayCalendar);
    }

    @Test
    void testIsPayableDayHoliday() {
        LocalDate holiday = LocalDate.of(2026, 1, 1);

        when(holidayCalendar.isHoliday(holiday)).thenReturn(true);

        assertFalse(service.isPayableDay(holiday));
    }

    @Test
    void testIsPayableDayNonHoliday() {
        LocalDate payableDay = LocalDate.of(2026, 1, 13);

        when(holidayCalendar.isHoliday(payableDay)).thenReturn(false);

        assertTrue(service.isPayableDay(payableDay));
    }

    @Test
    void testCountPayableDaysNoHolidays() {
        LocalDate vacationStartDay = LocalDate.of(2026, 1, 13);
        LocalDate vacationEndDay = LocalDate.of(2026, 1, 16);

        when(holidayCalendar.isHoliday(any(LocalDate.class))).thenReturn(false);

        int payableDays = service.countPayableDays(vacationStartDay, vacationEndDay);
        assertEquals(4, payableDays);

        for (int i = 0; i < 4; i++) {
            verify(holidayCalendar, times(1)).isHoliday(vacationStartDay.plusDays(1));
        }
    }

    @Test
    void testCountPayableDaysWithHolidays() {
        LocalDate vacationStartDay = LocalDate.of(2026, 11, 1);
        LocalDate vacationEndDay = LocalDate.of(2026, 11, 5);

        when(holidayCalendar.isHoliday(LocalDate.of(2026, 11, 1))).thenReturn(false);
        when(holidayCalendar.isHoliday(LocalDate.of(2026, 11, 2))).thenReturn(false);
        when(holidayCalendar.isHoliday(LocalDate.of(2026, 11, 3))).thenReturn(false);
        when(holidayCalendar.isHoliday(LocalDate.of(2026, 11, 4))).thenReturn(true);
        when(holidayCalendar.isHoliday(LocalDate.of(2026, 11, 5))).thenReturn(false);

        int payableDays = service.countPayableDays(vacationStartDay, vacationEndDay);
        assertEquals(4, payableDays);
    }

    @Test
    void testCountPayableDaysSingleHolidayDay() {
        LocalDate holiday = LocalDate.of(2026, 1, 1);

        when(holidayCalendar.isHoliday(holiday)).thenReturn(true);
        int payableDays = service.countPayableDays(holiday, holiday);

        assertEquals(0, payableDays);
    }
}
