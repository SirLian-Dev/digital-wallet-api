package com.tuusuario.wallet.service;

import com.tuusuario.wallet.model.*;
import com.tuusuario.wallet.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    
	    //Inyección por constructor
	    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
	        this.accountRepository = accountRepository;
	        this.transactionRepository = transactionRepository;
	    }

    // MÉTODO DE DEPÓSITO
    @Transactional
    public Account deposit(Long userId, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("El monto debe ser mayor a cero");
        }
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        
        account.setBalance(account.getBalance().add(amount));
        return accountRepository.save(account);
    }

    // MÉTODO DE TRANSFERENCIA
    @Transactional
    public void transfer(Long originUserId, Long destinationUserId, BigDecimal amount) {
    	// 1. Validar monto
	        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
	            throw new RuntimeException("El monto a transferir debe ser mayor a cero");
	        }
	        if (originUserId.equals(destinationUserId)) {
	            throw new RuntimeException("No puedes transferirte a ti mismo");
	        }
        // 2. Buscar AMBAS cuentas (con IDs diferentes)
        Account origin = accountRepository.findByUserId(originUserId)
                .orElseThrow(() -> new RuntimeException("Cuenta de origen no encontrada"));
        
        Account destination = accountRepository.findByUserId(destinationUserId)
                .orElseThrow(() -> new RuntimeException("Cuenta de destino no encontrada"));

	        // 3. Validar saldo suficiente
	        if (origin.getBalance().compareTo(amount) < 0) {
	            throw new RuntimeException("Saldo insuficiente en la cuenta de origen");
	        }
        // 4. Ejecutar movimiento
        origin.setBalance(origin.getBalance().subtract(amount));
        destination.setBalance(destination.getBalance().add(amount));
        // 5. Guardar cambios
        accountRepository.save(origin);
        accountRepository.save(destination);
        // 6. Registrar la transacción
        Transaction receipt = new Transaction();
        receipt.setAmount(amount);
        receipt.setType(TransactionType.TRANSFER);
        receipt.setTimestamp(LocalDateTime.now());
        receipt.setAccount(origin); // Quién envía
        receipt.setDestinationAccount(destination.getAccountNumber());
        
        transactionRepository.save(receipt);
    }
    
    //Lógica de Historial
    public List<Transaction> getTransactionHistory(Long userId) {
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return account.getTransactions();
    }
}