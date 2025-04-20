package com.example.demo.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime localDateTime,
                           String message,
                           String details,
                           String errorCode) {
}
