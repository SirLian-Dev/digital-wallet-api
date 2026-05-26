package com.sirlian.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sirlian.wallet.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	 //Método para buscar por email
	Optional<User> findByEmail(String email);
}
