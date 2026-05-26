package com.sirlian.wallet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sirlian.wallet.enums.TransactionType;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Transaction {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private BigDecimal amount;

	    @Column(nullable = false)
	    private LocalDateTime timestamp;
	    private String destinationAccount;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private TransactionType type;

	    // Relación: Muchas transacciones pertenecen a una cuenta
	    @ManyToOne
	    @JoinColumn(name = "account_id")
	    @JsonBackReference
	    private Account account;

	    // Para transferencias, guardamos la referencia del destinatario
	    private String description;
	    
}
