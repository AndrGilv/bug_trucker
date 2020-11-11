package com.example.but_trucker2.exception;

import com.example.but_trucker2.entity.termsDirectories.ErrorState;
import org.springframework.web.bind.annotation.PathVariable;

public class ErrorStateHistoryNotFoundException extends RuntimeException {

    public ErrorStateHistoryNotFoundException(Long id) {
        super("Could not find errorStateHistory item with id = " + id);
    }
}