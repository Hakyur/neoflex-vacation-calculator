package ru.rogotovsky.vacation.calculator.validator;

import ru.rogotovsky.vacation.calculator.dto.VacationCalculationRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VacationCalculationRequestValidator
        implements ConstraintValidator<ValidVacationCalculation, VacationCalculationRequest> {

    @Override
    public boolean isValid(
            VacationCalculationRequest request,
            ConstraintValidatorContext context
    ) {
        if (request == null) {
            return false;
        }

        boolean hasVacationDays = request.getVacationDays() != null;
        boolean hasStartDate = request.getVacationStartDay() != null;
        boolean hasEndDate = request.getVacationEndDay() != null;

        if (hasVacationDays) {
            return true;
        }

        if (hasStartDate && hasEndDate) {
            if (request.getVacationStartDay().isAfter(request.getVacationEndDay())) {
                addViolation(context, "vacationStartDay", "Start date must be before end date");
                return false;
            }
            return true;
        }

        addViolation(context, null, "Vacation days or date range must be provided");
        return false;
    }

    private void addViolation(ConstraintValidatorContext context, String field, String message) {
        context.disableDefaultConstraintViolation();

        if (field == null) {
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        } else {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(field)
                    .addConstraintViolation();
        }

    }
}
