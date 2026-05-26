package com.sirlian.wallet.service;

import org.springframework.stereotype.Service;

import com.sirlian.wallet.entity.Account;
import com.sirlian.wallet.entity.User;
import com.sirlian.wallet.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
	
    private final UserRepository userRepository;

	    // Inyección por constructor
	    public UserService(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }
    
    //Método para encontrar usuario por ID
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    //Método para obtener a todos los usuarios registrados
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    //Método para guardar nuevo Usuario
    public User saveUser(User user) {
    	//Se crea la Cuenta
    	Account newAccount = new Account();
        newAccount.setAccountNumber("ACC-" + System.currentTimeMillis());
        newAccount.setBalance(BigDecimal.ZERO);
        //Se establece una relacion bidireccional
        newAccount.setUser(user); 
        user.setAccount(newAccount);
        
        return userRepository.save(user);
    }
    
}