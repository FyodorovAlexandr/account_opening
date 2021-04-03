package com.accountopening.server.repository;

import com.accountopening.server.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpeningRepository extends JpaRepository<Account, Long> {
}
