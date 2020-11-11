package com.example.but_trucker2.entity.termsDirectories;

public enum ErrorCriticality {
    CRITICAL(1, "CRITICAL"),
    BLOCKING(2, "BLOCKING"),
    TRIVIAL(3, "TRIVIAL"),
    MEDIUM(4, "MEDIUM");

    int id;
    String criticality;
    ErrorCriticality(int i, String criticality) {
        this.id = i;
        this.criticality = criticality;
    }


}
