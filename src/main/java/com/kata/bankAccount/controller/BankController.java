package com.kata.bankAccount.controller;

import com.kata.bankAccount.controller.dto.AccountDto;
import com.kata.bankAccount.controller.dto.BankAccountDetails;
import com.kata.bankAccount.controller.dto.RecordDto;
import com.kata.bankAccount.controller.mapper.BankAccountMapper;
import com.kata.bankAccount.entity.Account;
import com.kata.bankAccount.entity.Record;
import com.kata.bankAccount.exception.BusinessException;
import com.kata.bankAccount.service.BankService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bankAccount/{idAccount}")
public class BankController {

    private final BankService bankService;
    private final BankAccountMapper bankAccountMapper;

    public BankController(BankService bankService, BankAccountMapper bankAccountMapper) {
        this.bankService = bankService;
        this.bankAccountMapper = bankAccountMapper;
    }

    /**
     * return account
     *
     * @param idAccount
     * @throws BusinessException
     */
    @GetMapping()
    AccountDto getAccount(@PathVariable Long idAccount) throws BusinessException {
        return bankAccountMapper.accountDtoFromEntity(bankService.getAccount(idAccount));
    }

    /**
     * add new record to account
     *
     * @param idAccount
     * @throws BusinessException
     */
    @PostMapping("/record/add/deposit")
    Record addDepositRecord(@PathVariable Long idAccount, @Valid @RequestBody RecordDto recordDto) throws BusinessException {
        return bankService.addRecordToAccount(idAccount, recordDto);
    }

    @PostMapping("/record/add/withdrawal")
    Record addWithDrawlRecord(@PathVariable Long idAccount, @Valid @RequestBody RecordDto recordDto) throws BusinessException {
        return bankService.addRecordToAccount(idAccount, recordDto);
    }

    /**
     * return all records
     *
     * @param idAccount
     * @throws BusinessException
     */
    @GetMapping("/records")
    BankAccountDetails getRecords(@PathVariable Long idAccount) throws BusinessException {
        Account account = bankService.getAccount(idAccount);
        return bankAccountMapper.bankAccountDetailsFromEntity(account, bankService.getRecordsByAccount(account));
    }

}
