package com.mrxyc.jdk8.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTester {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime parse2 = LocalDateTime.parse(now.toString());
        parse2 = parse2.plusMinutes(3);
        System.out.println(now.compareTo(parse2));
        LocalDate localDate = now.toLocalDate();
        System.out.println(localDate);
        LocalTime localTime = now.toLocalTime();
        System.out.println(localTime);
        Month month = now.getMonth();
        int dayOfMonth = now.getDayOfMonth();
        int second = now.getSecond();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        int dayOfYear = now.getDayOfYear();
        System.out.println("月: " + month + ", 日: " + dayOfMonth + ", 秒: " + second);
        System.out.println("星期: " + dayOfWeek.getValue());
        System.out.println("星期: " + dayOfWeek);
        System.out.println(dayOfYear);
        LocalDateTime localDateTime = now.withDayOfMonth(10).withYear(2012);
        System.out.println(localDateTime);
        LocalDate of = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println(of);
        LocalTime of1 = LocalTime.of(22, 15);
        System.out.println(of1);
        LocalTime parse = LocalTime.parse("20:15:30.151");
        System.out.println(parse);


        //时区
        ZonedDateTime now1 = ZonedDateTime.now();
        System.out.println(now1);
        ZonedDateTime parse1 = ZonedDateTime.parse("2019-07-22T20:47:04.198+08:00[Asia/Shanghai]");
        System.out.println(parse1);
        ZoneId of2 = ZoneId.of("Asia/Shanghai");
        System.out.println(of2);
        ZoneId of3 = ZoneId.of("Europe/Paris");
        System.out.println(of3);
        System.out.println(ZoneId.systemDefault());
        ZonedDateTime of4 = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), of3);
        System.out.println(of4);
        System.out.println(ZonedDateTime.now(of3));
    }
}
