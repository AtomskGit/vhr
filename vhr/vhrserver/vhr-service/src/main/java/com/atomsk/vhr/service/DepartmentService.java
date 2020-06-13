package com.atomsk.vhr.service;

import com.atomsk.vhr.mapper.DepartmentMapper;
import com.atomsk.vhr.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Atomsk
 * @github https://github.com/AtomskGit
 * @blog https://atomskgit.github.io/blog/
 * @date 2020/6/9
 */
@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    public void addDepartment(Department department) {
        department.setEnabled(true);
        departmentMapper.addDepartment(department);
    }

    public void deleteDepartment(Department department) {
        departmentMapper.deleteDepartmentById(department);
    }
}
