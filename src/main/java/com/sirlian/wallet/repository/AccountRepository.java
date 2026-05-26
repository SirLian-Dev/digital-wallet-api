package com.sirlian.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sirlian.wallet.entity.Account;

import java.util.Optional;

@Repository

public interface AccountRepository extends JpaRepository<Account, Long> {
    // Método para buscar la cuenta por su número único
    Optional<Account> findByUserId(Long userId);

}