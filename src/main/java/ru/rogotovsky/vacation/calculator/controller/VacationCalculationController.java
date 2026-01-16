package ru.rogotovsky.vacation.calculator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.rogotovsky.vacation.calculator.dto.ErrorResponse;
import ru.rogotovsky.vacation.calculator.dto.VacationCalculationRequest;
import ru.rogotovsky.vacation.calculator.dto.VacationCalculationResponse;
import ru.rogotovsky.vacation.calculator.service.VacationCalculationService;

import javax.validation.Valid;

@RestController
@Tag(name = "Vacation calculation", description = "API for calculating vacation pay")
public class VacationCalculationController {

    private final VacationCalculationService calculationService;

    public VacationCalculationController(VacationCalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @Operation(
            summary = "Calculate vacation pay",
            description = "Calculates vacation pay based on average monthly salary either " +
                    "number of vacation days or vacation start and end dates"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Vacation pay calculated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = VacationCalculationResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid vacation calculation request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/calculacte")
    public ResponseEntity<VacationCalculationResponse> calculate(@Valid @RequestBody VacationCalculationRequest request) {
        return ResponseEntity.ok(new VacationCalculationResponse(
                calculationService.calculateVacationPay(
                        request.getAverageSalary(),
                        request.getVacationDays(),
                        request.getVacationStartDay(),
                        request.getVacationEndDay()
                )));
    }
}
