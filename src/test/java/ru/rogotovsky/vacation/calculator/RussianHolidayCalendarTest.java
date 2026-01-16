package ru.rogotovsky.vacation.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.rogotovsky.vacation.calculator.calendar.HolidayCalendar;
import ru.rogotovsky.vacation.calculator.calendar.RussianHolidayCalendar;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RussianHolidayCalendarTest {

    private static final LocalDate HOLIDAY = LocalDate.of(2026, 1, 1);
    private static final LocalDate PAYABLE_DAY = LocalDate.of(2026, 1, 13);

    private HolidayCalendar holidayCalendar;

    @BeforeEach
    void setUp() {
        holidayCalendar = new RussianHolidayCalendar();
    }

    @Test
    void testIsHolidayTrue() {
        boolean actual = holidayCalendar.isHoliday(HOLIDAY);

        assertTrue(actual);
    }

    @Test
    void testIsHolidayFalse() {
        boolean actual = holidayCalendar.isHoliday(PAYABLE_DAY);

        assertFalse(actual);
    }
}
