package com.atomsk.vhr.controller.system.basic;

import com.atomsk.vhr.model.Menu;
import com.atomsk.vhr.model.RespBean;
import com.atomsk.vhr.model.Role;
import com.atomsk.vhr.service.MenuRoleService;
import com.atomsk.vhr.service.MenuService;
import com.atomsk.vhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Atomsk
 * @github https://github.com/AtomskGit
 * @blog https://atomskgit.github.io/blog/
 * @date 2020/6/6
 */
@RestController
@RequestMapping("/system/basic/role")
public class RoleManagerController {

    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;
    @Autowired
    MenuRoleService menuRoleService;

    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("/mids/{rid}")
    public List<Integer> getMenuIdByRoleId(@PathVariable Integer rid) {
        return menuRoleService.getMenuIdByRoleId(rid);
    }

    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        if (menuRoleService.updateMenuRole(rid, mids)) {
            return RespBean.ok("更新权限成功！");
        } else {
            return RespBean.error("更新权限失败！");
        }
    }

    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role) {
        if (roleService.addRole(role) == 1) {
            return RespBean.ok("添加角色成功！");
        } else {
            return RespBean.error("添加失败！");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteRole(@PathVariable Integer id) {
        if (roleService.deleteRole(id) == 1) {
            return RespBean.ok("删除角色成功！");
        } else {
            return RespBean.error("删除失败！");
        }
    }

}
