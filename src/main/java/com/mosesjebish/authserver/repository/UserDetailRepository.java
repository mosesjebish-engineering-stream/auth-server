package com.mosesjebish.authserver.repository;

import com.mosesjebish.authserver.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String name);
}
