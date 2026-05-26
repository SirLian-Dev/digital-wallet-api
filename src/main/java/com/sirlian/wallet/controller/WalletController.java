package com.tuusuario.wallet.controller;

import com.tuusuario.wallet.dto.TransferRequest;
import com.tuusuario.wallet.service.AccountService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final AccountService accountService;

    public WalletController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest request) {
    	accountService.transfer(
                request.getOriginUserId(), 
                request.getDestinationUserId(), 
                request.getAmount()
            );
        return ResponseEntity.ok("Transferencia realizada con éxito");
    }
}