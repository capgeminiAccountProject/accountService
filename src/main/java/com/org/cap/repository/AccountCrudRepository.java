package com.org.cap.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.org.cap.dto.Account;

@Transactional
@Repository
public interface AccountCrudRepository 
  extends CrudRepository<Account, Integer> {
}
