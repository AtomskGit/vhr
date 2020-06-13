package com.atomsk.vhr.controller.salary;

import com.atomsk.vhr.model.RespBean;
import com.atomsk.vhr.model.RespPageBean;
import com.atomsk.vhr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Atomsk
 * @github https://github.com/AtomskGit
 * @blog https://atomskgit.github.io/blog/
 * @date 2020/6/13
 */
@RestController
@RequestMapping("/salary/emp")
public class EmpSalaryController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public RespPageBean getEmpSalaryByPage(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer size){

        return employeeService.getEmpSalaryByPage(page, size);
    }

    @PutMapping("/")
    public RespBean updateEmpSalary(Integer eid, Integer sid) {
        Integer result = employeeService.updateEmpSalary(eid, sid);
        if (result == 1 || result == 2) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

}
