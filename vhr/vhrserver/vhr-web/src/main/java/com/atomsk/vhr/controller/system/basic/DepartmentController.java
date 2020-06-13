package com.atomsk.vhr.controller.system.basic;

import com.atomsk.vhr.model.Department;
import com.atomsk.vhr.model.RespBean;
import com.atomsk.vhr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Atomsk
 * @github https://github.com/AtomskGit
 * @blog https://atomskgit.github.io/blog/
 * @date 2020/6/9
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/")
    public RespBean addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
        if (department.getResult() == 1) {
            return RespBean.ok("添加部门成功！", department);
        } else {
            return RespBean.error("添加失败！", department);
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDepartment(@PathVariable Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentService.deleteDepartment(department);
        Integer result = department.getResult();
        if (result == 1) {
            return RespBean.ok("删除成功！");
        } else if (result == -2) {
            return RespBean.error("当前部门下还有部门，删除失败！");
        } else if (result == -1) {
            return RespBean.error("当前部门下还有员工，删除失败");
        }
        return RespBean.error("未知错误！");
    }
}
