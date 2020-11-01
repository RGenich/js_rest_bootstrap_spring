package com.jm.genich.bootfirst.dao;

import com.jm.genich.bootfirst.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


//    void deleteUser(Integer id);

//    void updateUser(User user);

//    User getUser(Integer id);

//    List<User> showUsers();

    User getUserByLogin (String login);
}
