package com.bpop.kaisen.repositories;

import com.bpop.kaisen.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdentificationAndPass(Long userId, String pass);
}
