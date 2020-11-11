package com.example.but_trucker2.entity.termsDirectories;

public enum ErrorState {
    DETECTED(1, "DETECTED"),
    IN_PROCESS(2, "IN_PROCESS"),
    DEFERRED(3, "DEFERRED"),
    FIXED(4, "FIXED"),
    CLOSED(5, "CLOSED"),
    CANNOT_BE_FIXED(6, "CANNOT_BE_FIXED"),
    NOT_AN_ERROR(7, "NOT_AN_ERROR");

    int id;
    String state;
    ErrorState(int i, String state) {
        this.id = i;
        this.state = state;
    }
}
