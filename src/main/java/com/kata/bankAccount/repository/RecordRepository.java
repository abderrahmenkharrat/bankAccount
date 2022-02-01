package com.kata.bankAccount.repository;

import com.kata.bankAccount.entity.Account;
import com.kata.bankAccount.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
	List<Record> findByAccount(Account account);
}
