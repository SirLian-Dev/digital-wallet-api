package com.tuusuario.wallet.repository;

import com.tuusuario.wallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
    // Método para obtener el historial de una cuenta específica ordenado por fecha
    List<Transaction> findByAccountId(Long accountId);
}