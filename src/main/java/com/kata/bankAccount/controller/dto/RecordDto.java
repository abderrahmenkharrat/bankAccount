package com.kata.bankAccount.controller.dto;

import com.kata.bankAccount.entity.RecordType;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class RecordDto {
	private Long id;
	@Positive(message = "amount should be positive")
	private double amount;
	private RecordType type;
	private LocalDateTime date;
	private Long account;

}
