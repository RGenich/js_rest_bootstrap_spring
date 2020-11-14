package com.jm.genich.bootfirst.service;

import com.jm.genich.bootfirst.dao.RoleDAOImpl;
import com.jm.genich.bootfirst.dao.UserRepository;
import com.jm.genich.bootfirst.models.Role;
import com.jm.genich.bootfirst.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleDAOImpl roleDAO;
    @Autowired
    RoleServiceImpl roleServiceImpl;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void regUser(User user) {

        Set<Role> roles = new HashSet<>();
        if (!existsById(1L)) {
            roleServiceImpl.createRoles();
            roles.add(roleServiceImpl.getRoleByName("ROLE_ADMIN"));
        }
        roles.add(roleServiceImpl.getRoleByName("ROLE_USER"));
        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUser(Long id) {
        Optional opt = userRepository.findById(id);
        return (User) opt.get();
    }

    @Override
    public void updateUser(User updatedUserObject) {
        //забыл зачем это? видимо чтобы не записывать новые экземпляры ролей в БД, пришедшие из view?
        Set<Role> dbRoles = new HashSet<>();
        for (Role role : updatedUserObject.getRoles()) {
            dbRoles.add(roleDAO.getRoleByName(role.getRoleName()));
        }
        updatedUserObject.setRoles(dbRoles);
        if (updatedUserObject.getPassword() == null) {
            //если пароль не задан, вставляем старый зашифрованный
            updatedUserObject.setPassword(userRepository.getOne(updatedUserObject.getId()).getPassword());
        }

        userRepository.save(updatedUserObject);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByLogin(String login) {

        return userRepository.getUserByLogin(login);
    }

//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return userDao.getUserByLogin(s);
//    }
}
