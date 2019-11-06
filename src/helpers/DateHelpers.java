/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author thiago
 */
public class DateHelpers {
    public static String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
    
    public static int diffInSeconds(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        LocalDateTime now = LocalDateTime.now();
        return (int)dateTime.until(now, ChronoUnit.SECONDS);
    }
}
