package com.jm.genich.bootfirst.dao;

import com.jm.genich.bootfirst.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByLogin (String login);
}
