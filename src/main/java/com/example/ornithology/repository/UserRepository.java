package com.example.ornithology.repository;

import com.example.ornithology.models.AuthenticationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AuthenticationModel, Long> {

    UserDetails findByLogin(String login);
}