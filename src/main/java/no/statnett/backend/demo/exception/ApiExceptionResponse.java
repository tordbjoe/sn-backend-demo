package no.statnett.backend.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiExceptionResponse {

    private LocalDateTime timestamp;
    private String message;
    private String details;
}
