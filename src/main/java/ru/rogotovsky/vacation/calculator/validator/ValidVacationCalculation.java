package ru.rogotovsky.vacation.calculator.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = VacationCalculationRequestValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidVacationCalculation {

    String message() default "Invalid vacation calculation request";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
