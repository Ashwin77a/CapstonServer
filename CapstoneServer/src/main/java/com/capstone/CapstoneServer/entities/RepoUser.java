package com.capstone.CapstoneServer.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUser extends JpaRepository<User, Integer> {

    User findByUserName(String username);
    User findByEmail(String email);

}