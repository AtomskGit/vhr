package com.atomsk.vhr.controller.system.basic;

import com.atomsk.vhr.model.JobLevel;
import com.atomsk.vhr.model.RespBean;
import com.atomsk.vhr.service.JobLevelService;
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
@RequestMapping("/system/basic/jobLevel")
public class JobLevelController {
    @Autowired
    JobLevelService jobLevelService;

    @GetMapping("/")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel) {
        if (jobLevelService.addJobLevel(jobLevel) == 1) {
            return RespBean.ok("新增成功！");
        } else {
            return RespBean.error("新增失败！");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable Integer id) {
        if (jobLevelService.deleteJobLevel(id) == 1) {
            return RespBean.ok("删除成功！");
        } else {
            return RespBean.error("删除失败！");
        }
    }

    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody JobLevel jobLevel) {
        if (jobLevelService.updateJobLevel(jobLevel) == 1) {
            return RespBean.ok("更新成功！");
        } else {
            return RespBean.error("更新失败！");
        }
    }

    @DeleteMapping("/")
    public RespBean deleteJobLevelByArray(Integer[] ids) {
        if (jobLevelService.deleteJobLevelByArray(ids) == ids.length) {
            return RespBean.ok("批量删除成功！");
        } else {
            return RespBean.error("批量删除失败！");
        }
    }
}
