package com.kata.bankAccount.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Record {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private double amount;
	@Enumerated(EnumType.STRING)
	private RecordType type;
	@Column(nullable = false, updatable = false)
    @CreatedDate
	private LocalDateTime date;
	@ManyToOne()
	@JoinColumn(name = "accountId")
	private Account account;


	
}
