package com.otto.lab2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.time.Instant;

@Value
public class TableRow {
    double x;
    double y;
    int r;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    Instant currentTime;
    double executionTime;
    boolean result;
}
