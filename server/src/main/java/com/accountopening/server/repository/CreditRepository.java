package com.accountopening.server.repository;

import com.accountopening.server.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    Optional<Credit> findByPassport(String passport);
}
