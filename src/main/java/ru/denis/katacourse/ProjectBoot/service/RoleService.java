package ru.denis.katacourse.ProjectBoot.service;

import org.springframework.stereotype.Service;
import ru.denis.katacourse.ProjectBoot.dao.RoleDAO;
import ru.denis.katacourse.ProjectBoot.model.Role;

import java.util.List;

public interface RoleService {


    Role getRole(String userRole);

    Role getRoleById(Long id);

    List<Role> allRoles();


}
