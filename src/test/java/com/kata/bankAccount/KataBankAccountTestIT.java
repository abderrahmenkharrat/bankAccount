package com.kata.bankAccount;


import com.kata.bankAccount.controller.dto.RecordDto;
import com.kata.bankAccount.entity.Account;
import com.kata.bankAccount.entity.Customer;
import com.kata.bankAccount.entity.Record;
import com.kata.bankAccount.exception.BusinessException;
import com.kata.bankAccount.service.BankService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.kata.bankAccount.entity.RecordType.DEPOSIT;
import static com.kata.bankAccount.entity.RecordType.WITHDRAWAL;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {KataBankAccountApplication.class})
public class KataBankAccountTestIT {

    @Autowired
    BankService bankService;

    @Before
    public void initialize() throws BusinessException {
        Customer customer = new Customer();
        Account account = Account.builder()
                .client(customer)
                .balance(15000)
                .build();
        bankService.addCustomer(customer);
        bankService.addAccount(account);
        bankService.addRecordToAccount(account.getId(), RecordDto.builder()
                .amount(1000)
                .type(DEPOSIT)
                .build());
        bankService.addRecordToAccount(account.getId(), RecordDto.builder()
                .amount(1500)
                .type(WITHDRAWAL)
                .build());
        bankService.addRecordToAccount(account.getId(), RecordDto.builder()
                .amount(200)
                .type(DEPOSIT)
                .build());
    }

    @Test
    public void should_return_history_of_records_for_given_account() throws BusinessException {

        // when
        List<Record> records = bankService.getRecordsByAccount(bankService.getAccount(Long.valueOf(2)));

        // then
        assertThat(records).hasSize(3);
        assertThat(records.stream()
                .map(Record::getAmount)
                .collect(toList())).containsExactlyInAnyOrder(1000.0, 1500.0, 200.0);
        assertThat(records.stream()
                .map(Record::getType)
                .collect(toList())).containsExactlyInAnyOrder(DEPOSIT, WITHDRAWAL, DEPOSIT);

    }
}
