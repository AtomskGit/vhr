package com.atomsk.vhr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Atomsk
 * @github https://github.com/AtomskGit
 * @blog https://atomskgit.github.io/blog/
 * @date 2020/6/4
 */
@RestController
public class HelloController {
    @GetMapping("/personnel/emp/hello")
    public String getEmpHello() {
        return "员工资料";
    }

    @GetMapping("//personnel/rap/hello")
    public String getEmpRaPHello() {
        return "员工奖惩";
    }
}
