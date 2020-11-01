package com.jm.genich.bootfirst.service;

import com.jm.genich.bootfirst.dao.RoleDAOImpl;
import com.jm.genich.bootfirst.dao.UserRepository;
import com.jm.genich.bootfirst.models.Role;
import com.jm.genich.bootfirst.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userDao;
    @Autowired
    RoleDAOImpl roleDAO;
    @Autowired
    RoleServiceImpl roleServiceImpl;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void addUser(User user) {

        Set<Role> roles = new HashSet<>();
        if (!existsById(1L)) {
            roleServiceImpl.createRoles();
            roles.add(roleServiceImpl.getRoleByName("ROLE_ADMIN"));
        }
        roles.add(roleServiceImpl.getRoleByName("ROLE_USER"));
        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public boolean existsById(Long id) {
        return userDao.existsById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getOne(id);
    }

    @Override
    public void updateUser(User updatedUserObject) {
        //забыл зачем это? видимо чтобы не записывать новые экземпляры ролей в БД, пришедшие из view?
        Set<Role> dbRoles = new HashSet<>();
        for (Role role : updatedUserObject.getRoles()) {
            dbRoles.add(roleDAO.getRoleByName(role.getRoleName()));
        }
        updatedUserObject.setRoles(dbRoles);
        userDao.save(updatedUserObject);
    }

    @Override
    public List<User> showUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserByLogin(String login) {

        return userDao.getUserByLogin(login);
    }

//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return userDao.getUserByLogin(s);
//    }
}
