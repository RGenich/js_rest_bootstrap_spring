package com.jm.genich.bootfirst.dao;

import com.jm.genich.bootfirst.models.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    EntityManager entityManager;

    public Role getRoleByName(String name) {
        return (Role) entityManager.createQuery("select r from Role r where r.roleName=:name")
                .setParameter("name", name)
                .getSingleResult();
    }

    public void createRoles(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> getAllRoles() {

        return (List<Role>) entityManager.createQuery("SELECT r from Role r").getResultList();
    }

}
