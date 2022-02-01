package com.kata.bankAccount.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BankAccountDetails {
    private List<RecordDto> recordDtos;
    private double balance;
    private Long accountId;
}
