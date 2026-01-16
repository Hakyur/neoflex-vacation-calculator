package ru.rogotovsky.vacation.calculator.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Error response returned when request processing fails")
public class ErrorResponse {

    @Schema(
            description = "Timestamp when the error occurred",
            example = "2026-01-16T15:21:21"
    )
    private LocalDateTime timestamp;

    @Schema(
            description = "HTTP status code",
            example = "400"
    )
    private int status;

    @Schema(
            description = "Short error description",
            example = "Bad request"
    )
    private String error;

    @Schema(
            description = "Detailed error message",
            example = "Vacation days must be positive"
    )
    private String message;

    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
