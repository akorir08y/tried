package com.example.tried.auth.dates;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class DateRange {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;
}