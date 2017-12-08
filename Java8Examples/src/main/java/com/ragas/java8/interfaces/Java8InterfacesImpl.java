package com.ragas.java8.interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public class Java8InterfacesImpl implements Java8Interfaces {

    private LocalDateTime dateAndTime;

    public Java8InterfacesImpl() {
        dateAndTime = LocalDateTime.now();
    }

    public void setTime(int hour, int minute, int second) {
        LocalDate currentDate = LocalDate.from(dateAndTime);
        LocalTime timeToSet = LocalTime.of(hour, minute, second);
        dateAndTime = LocalDateTime.of(currentDate, timeToSet);
    }

    public void setDate(int day, int month, int year) {
        LocalDate dateToSet = LocalDate.of(day, month, year);
        LocalTime currentTime = LocalTime.from(dateAndTime);
        dateAndTime = LocalDateTime.of(dateToSet, currentTime);
    }

    public void setDateAndTime(int day, int month, int year,
                               int hour, int minute, int second) {
        LocalDate dateToSet = LocalDate.of(day, month, year);
        LocalTime timeToSet = LocalTime.of(hour, minute, second);
        dateAndTime = LocalDateTime.of(dateToSet, timeToSet);
    }

    public LocalDateTime getLocalDateTime() {
        return dateAndTime;
    }

    public String toString() {
        return dateAndTime.toString();
    }

    public ZonedDateTime getZonedDateTime(String zoneString) {
        System.out.println("Overriding DEFAULT method");
        return ZonedDateTime.now();
    }

    public static void main(String... args) {
        Java8Interfaces myTimeClient = new Java8InterfacesImpl();
        System.out.println("Current time: " + myTimeClient.toString());
        // Calling the new method defined in the interface
        System.out.println("Time in California: " +
                myTimeClient.getZonedDateTime("CA Time").toString());
    }

}
