package com.ragas.java8.interfaces;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * In this interface we are using the DEFAULT keyword and the rules around using it in interfaces
 *
 * Default methods enable us to add new functionality to the interfaces of existing libraries and ensure binary
 * compatibility with code written for older versions of those interfaces.
 *
 */
public interface Java8Interfaces {
    void setTime(int hour, int minute, int second);
    void setDate(int day, int month, int year);
    void setDateAndTime(int day, int month, int year, int hour, int minute, int second);
    LocalDateTime getLocalDateTime();

    /**
     * Consider a simple interface TimeClient, as defined above. All the implementation classes would have implemented
     * the defined methods or made the class abstract with providing implementation to only one or two methods.
     * SimpleTimeClient class has implementation for all the methods defined above.
     *
     * Suppose we want to add new functionality to the TimeClient interface, such as the ability to specify a
     * time zone through a ZonedDateTime object.
     *
     * ZonedDateTime getZonedDateTime(String zoneString);
     *
     * For above modification to the TimeClient interface, we would have to modify the implementing class in
     * this case SimpleTimeClient and implement the method getZonedDateTime.
     *
     * However, rather than leaving getZonedDateTime as abstract, we can instead provide a default implementation by
     * specifying a method definition in an interface as DEFAULT with the default keyword at the beginning .
     *
     * Now with this change to the interface, you do not have to modify the implementing (SimpleTimeClient) class,
     * for that matter any class that implements the interface TimeClient. It will have the method getZonedDateTime
     * already defined and can be directly used.
     *
     * When you extend an interface that contains a default method, you can do the following:
     * 1. Not mention the default method at all, which lets your extended interface inherit the default method.
     * 2. Redeclare the default method, which makes it abstract.
     * 3. Redefine the default method, which overrides it.
     *
     */

    default ZonedDateTime getZonedDateTime(String zoneString) {
        return ZonedDateTime.of(getLocalDateTime(), getZoneId(zoneString));
    }

    /**
     * In addition to default methods, we can define static methods in interfaces. This makes it easier for us to
     * organize helper methods in our libraries; we can keep static methods specific to an interface in the same
     * interface rather than in a separate class.
     *
     * Below method defines a static method that retrieves a ZoneId object corresponding to a time zone identifier;
     * it uses the system default time zone if there is no ZoneId object corresponding to the given identifier.
     * As a result, we can simplify the method getZonedDateTime defined above.
     *
     */

    static ZoneId getZoneId (String zoneString) {
        try {
            return ZoneId.of(zoneString);
        } catch (DateTimeException e) {
            System.err.println("Invalid time zone: " + zoneString + "; using default time zone instead.");
            return ZoneId.systemDefault();
        }
    }
}
