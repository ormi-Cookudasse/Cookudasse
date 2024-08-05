package com.ormi.cookudasse.auth.repository;

import com.ormi.cookudasse.auth.domain.Role;
import com.ormi.cookudasse.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findAllByRole(Role role);

    List<User> findAllByRole(Role role);

    boolean existsByEmail(String email);
}