package com.tuusuario.wallet.repository;

import com.tuusuario.wallet.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository

public interface AccountRepository extends JpaRepository<Account, Long> {
    // Método para buscar la cuenta por su número único
    Optional<Account> findByUserId(Long userId);

}