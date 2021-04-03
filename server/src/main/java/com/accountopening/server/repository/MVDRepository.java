package com.accountopening.server.repository;

import com.accountopening.server.entity.MVD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MVDRepository extends JpaRepository<MVD, Long> {
    Optional<MVD> findByPassport(String passport);
}
