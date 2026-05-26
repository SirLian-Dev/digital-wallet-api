package com.sirlian.wallet.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sirlian.wallet.entity.Account;
import com.sirlian.wallet.entity.Transaction;
import com.sirlian.wallet.entity.User;
import com.sirlian.wallet.service.AccountService;
import com.sirlian.wallet.service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;
    private final AccountService accountService;
    //Inyección por constructor.
	    public UserController(UserService userService, AccountService accountService) {
	        this.accountService=accountService;
	        this.userService=userService;
	    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    	System.out.println("Recibiendo petición para crear usuario: " + user.getUsername());
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }
    
    @PatchMapping("/{id}/deposit")
    public Account deposit(@PathVariable Long id, @RequestBody Map<String, BigDecimal> request) {
        BigDecimal amount = request.get("amount");
        return accountService.deposit(id, amount);
    }
    
    // Retorna la lista de transacciones asociadas
    @GetMapping("/accounts/{userId}/transactions")
    public ResponseEntity<List<Transaction>> getHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getTransactionHistory(userId));
    }
}