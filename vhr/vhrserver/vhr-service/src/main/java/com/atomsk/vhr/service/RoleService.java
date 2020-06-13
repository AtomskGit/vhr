package com.atomsk.vhr.service;

import com.atomsk.vhr.mapper.RoleMapper;
import com.atomsk.vhr.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Atomsk
 * @github https://github.com/AtomskGit
 * @blog https://atomskgit.github.io/blog/
 * @date 2020/6/6
 */
@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    public Integer addRole(Role role) {
        if (!role.getName().startsWith("Role_")) {
            role.setName("Role_" + role.getName());
        }
        return roleMapper.insertSelective(role);
    }

    public int deleteRole(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }
}
