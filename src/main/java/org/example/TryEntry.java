package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TryEntry {
    Car car;
    Entry entry;
    String dt;

    boolean result;

    public TryEntry(Car car, Entry entry, String dt, boolean result) {
        this.car = car;
        this.entry = entry;
        this.dt = dt;
        this.result = result;
    }

    public TryEntry(Car car, Entry entry, boolean result) {
        this(car, entry, LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm")), result);
        this.car = car;
        this.entry = entry;
        this.dt = dt;
        this.result = result;
    }




}
