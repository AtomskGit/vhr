package com.atomsk.vhr.controller.salary;

import com.atomsk.vhr.model.RespBean;
import com.atomsk.vhr.model.Salary;
import com.atomsk.vhr.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Atomsk
 * @github https://github.com/AtomskGit
 * @blog https://atomskgit.github.io/blog/
 * @date 2020/6/12
 */
@RestController
@RequestMapping("/salary/payroll")
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @GetMapping("/")
    public List<Salary> getAllSalaries() {
        return salaryService.getAllSalaries();
    }

    @PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary){
        if (salaryService.addSalary(salary) == 1) {
            return RespBean.ok("添加账套成功！");
        }else{
            return RespBean.error("添加失败！");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteSalary(@PathVariable Integer id) {
        if (salaryService.deleteSalary(id) == 1) {
            return RespBean.ok("删除账套成功！");
        } else {
            return RespBean.error("删除失败！");
        }
    }

    @PutMapping("/")
    public RespBean updateSalary(@RequestBody Salary salary) {
        if (salaryService.updateSalary(salary) == 1) {
            return RespBean.ok("更新账套成功！");
        } else {
            return RespBean.error("更新失败！");
        }
    }
}
