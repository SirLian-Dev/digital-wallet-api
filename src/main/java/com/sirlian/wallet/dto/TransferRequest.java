package com.tuusuario.wallet.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransferRequest {
	private Long originUserId;
    private Long destinationUserId;
    private BigDecimal amount;
}
