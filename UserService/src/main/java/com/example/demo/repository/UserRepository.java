package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a {@link User} entity by its username.
     *
     * @param username The username of the user to search for.
     *                 Must not be {@code null} or empty.
     * @return The {@link User} entity if found, or {@code null} if no user exists with the given username.
     * @see User
     */
    User findByUsername(String username);
    
    /**
     * Checks if a username already exists in the repository.
     *
     * @param username The username to check for existence.
     *                 Must not be {@code null} or empty.
     * @return {@code true} if the username exists, {@code false} otherwise.
     */
    boolean existsByUsername(String username);

	List<User> findByUserId(Long userId);
}
