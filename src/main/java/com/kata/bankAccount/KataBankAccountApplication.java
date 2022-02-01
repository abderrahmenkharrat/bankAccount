package com.kata.bankAccount;

import com.kata.bankAccount.controller.dto.RecordDto;
import com.kata.bankAccount.entity.Account;
import com.kata.bankAccount.entity.Customer;
import com.kata.bankAccount.entity.RecordType;
import com.kata.bankAccount.service.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KataBankAccountApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KataBankAccountApplication.class, args);	
	}

	@Bean
	public CommandLineRunner run(BankService bankService) {
		Customer customer = new Customer();
		Account account = Account.builder()
				.client(customer)
				.balance(15000)
				.build();
		return args -> {
			bankService.addCustomer(customer);
			bankService.addAccount(account);
			bankService.addRecordToAccount(account.getId(), RecordDto.builder()
					.amount(1000)
					.type(RecordType.DEPOSIT)
					.build());
			bankService.addRecordToAccount(account.getId(), RecordDto.builder()
					.amount(1500)
					.type(RecordType.WITHDRAWAL)
					.build());
			bankService.addRecordToAccount(account.getId(), RecordDto.builder()
					.amount(200)
					.type(RecordType.DEPOSIT)
					.build());
		};
	}
}
