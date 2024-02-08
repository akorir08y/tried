package com.example.tried.dto.account;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class OfferStatement {

    String phone_number;
    String start_date;
    String end_date;
    String pin;
}
