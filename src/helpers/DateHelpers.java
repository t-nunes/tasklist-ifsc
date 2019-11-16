/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe que abstrai a complexidade de conversão de datas
 * @author thiago
 */
public class DateHelpers {
    static final String localDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * Retorna a data atual em string
     * @return {String} yyyy-MM-dd HH:mm
     */
    public static String now() {
        SimpleDateFormat formatter = new SimpleDateFormat(DateHelpers.localDateTimeFormat);
        return formatter.format(new Date());
    }
    
    /**
     * Retorna a diferença em segundos de uma data
     * @param date
     * @example
     * DateHelpers.diffInSeconds("2019-12-20 20:00");
     * @return {String} 
     */
    public static long diffInSeconds(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateHelpers.localDateTimeFormat);
        
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        LocalDateTime now = LocalDateTime.now();
        
        return ChronoUnit.SECONDS.between(dateTime, now);
    }
    
    /**
     * Retorna um localDateTime passando uma data em string
     * @param date
     * @return 
     */
    public static LocalDateTime localDateToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateHelpers.localDateTimeFormat);
        return LocalDateTime.parse(date, formatter);
    }
    
    public static int[] splitToComponentTimes(BigDecimal biggy) {
        long longVal = biggy.longValue();
        int hours = (int) longVal / 3600;
        int remainder = (int) longVal - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;

        int[] ints = {hours , mins , secs};
        return ints;
    }
}
