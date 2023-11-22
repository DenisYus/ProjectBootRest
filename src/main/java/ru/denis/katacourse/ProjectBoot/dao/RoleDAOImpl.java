package ru.denis.katacourse.ProjectBoot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.denis.katacourse.ProjectBoot.model.Role;

import java.util.List;
@Repository
public class RoleDAOImpl implements RoleDAO{
    @PersistenceContext()
    private EntityManager entityManager;
    @Override
    public Role getRole(String userRole) {
        Role role = null;
        try {
            role = entityManager
                    .createQuery("SELECT r FROM Role r WHERE r.userRole=:userRole", Role.class)
                    .setParameter("userRole", userRole)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Role not found!");
        }
        return role;
    }

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> allRoles() {
        return entityManager.createQuery("from Role ", Role.class).getResultList();
    }

    @Override
    public Role getDefaultRole() {
        return getRole("ROLE_USER");
    }
}
