package com.example.tried.auth.dates;




import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;




public class DateRange {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;
}