package com.sirlian.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sirlian.wallet.entity.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
    // Método para obtener el historial de una cuenta específica ordenado por fecha
    List<Transaction> findByAccountId(Long accountId);
}