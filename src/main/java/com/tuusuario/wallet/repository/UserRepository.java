package com.tuusuario.wallet.repository;

import com.tuusuario.wallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	 //Método para buscar por email
	Optional<User> findByEmail(String email);
}
