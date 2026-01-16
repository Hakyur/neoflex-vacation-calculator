package ru.rogotovsky.vacation.calculator.service;

import org.springframework.stereotype.Service;
import ru.rogotovsky.vacation.calculator.calendar.HolidayCalendar;

import java.time.LocalDate;

@Service
public class VacationPayableDayService {

    private final HolidayCalendar holidayCalendar;

    public VacationPayableDayService(HolidayCalendar holidayCalendar) {
        this.holidayCalendar = holidayCalendar;
    }

    public int countPayableDays(LocalDate vacationStartDay, LocalDate vacationEndDay) {
        int payableDays = 0;
        LocalDate currentDate = vacationStartDay;

        while (!currentDate.isAfter(vacationEndDay)) {
            if (isPayableDay(currentDate)) {
                payableDays++;
            }
            currentDate = currentDate.plusDays(1);
        }

        return payableDays;
    }

    public boolean isPayableDay(LocalDate day) {
        return !holidayCalendar.isHoliday(day);
    }
}
