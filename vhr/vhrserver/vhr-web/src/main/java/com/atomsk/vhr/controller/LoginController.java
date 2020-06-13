package com.atomsk.vhr.controller;

import com.atomsk.vhr.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Atomsk
 * @github https://github.com/AtomskGit
 * @blog https://atomskgit.github.io/blog/
 * @date 2020/6/4
 */
public class LoginController {
    @GetMapping("/login")
    public RespBean login() {
        return RespBean.error("尚未登录，请先登录！");
    }
}
