//package com.jm.genich.bootfirst.dao;
//
//import com.jm.genich.bootfirst.models.User;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Transactional
//@Repository
//public class UserReoisitoryImp implements UserRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public void deleteUser(Integer id) {
//        entityManager.remove(entityManager.find(User.class, id));
//    }
//
//
//    @Override
//    public void updateUser(User user) {
//        entityManager.merge(user);
//    }
//
//    @Override
//    public com.jm.genich.bootfirst.models.User getUser(Integer id) {
//        return entityManager.find(User.class, id);
//    }
//
//    @Override
//    public List<User> showUsers() {
//        return entityManager.createQuery("from User").getResultList();
//    }
//
//    @Override
//    public User getUserByLogin(String login) {
//
//        return (User) entityManager.createQuery("select u from User u where u.login=:login")
//                .setParameter("login", login)
//                .getSingleResult();
//    }
//}
