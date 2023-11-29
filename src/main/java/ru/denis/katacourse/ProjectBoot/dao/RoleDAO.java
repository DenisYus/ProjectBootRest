package ru.denis.katacourse.ProjectBoot.dao;

import org.springframework.stereotype.Repository;
import ru.denis.katacourse.ProjectBoot.model.Role;
import ru.denis.katacourse.ProjectBoot.model.User;

import java.util.List;

public interface RoleDAO {
    Role getRole(String userRole);

    Role getRoleById(Long id);

    List<Role> allRoles();

    void addRole(Role role);
}

