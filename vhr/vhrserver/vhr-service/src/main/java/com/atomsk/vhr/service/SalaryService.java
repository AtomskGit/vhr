package com.atomsk.vhr.service;

import com.atomsk.vhr.mapper.SalaryMapper;
import com.atomsk.vhr.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Atomsk
 * @github https://github.com/AtomskGit
 * @blog https://atomskgit.github.io/blog/
 * @date 2020/6/12
 */
@Service
public class SalaryService {

    @Autowired
    SalaryMapper salaryMappers;

    public List<Salary> getAllSalaries() {
        return salaryMappers.getAllSalaries();
    }

    public Integer addSalary(Salary salary){
        salary.setCreateDate(new Date());
        return salaryMappers.insertSelective(salary);
    }


    public Integer updateSalary(Salary salary) {
        return salaryMappers.updateByPrimaryKeySelective(salary);
    }

    public Integer deleteSalary(Integer id) {
        return salaryMappers.deleteByPrimaryKey(id);
    }
}
