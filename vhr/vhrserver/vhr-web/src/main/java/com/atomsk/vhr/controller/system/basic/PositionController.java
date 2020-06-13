package com.atomsk.vhr.controller.system.basic;

import com.atomsk.vhr.model.RespBean;
import com.atomsk.vhr.service.PositionService;
import com.atomsk.vhr.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Atomsk
 * @github https://github.com/AtomskGit
 * @blog https://atomskgit.github.io/blog/
 * @date 2020/6/5
 */
@RestController
@RequestMapping("/system/basic/position")
public class PositionController {
    @Autowired
    PositionService positionService;

    @GetMapping("/")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position) {
        if (positionService.addPosition(position) == 1) {
            return RespBean.ok("添加成功！");
        } else {
            return RespBean.error("添加失败！");
        }
    }

    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position) {
        if (positionService.updatePosition(position) == 1) {
            return RespBean.ok("更新成功！");
        } else {
            return RespBean.error("更新失败！");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id) {
        if (positionService.deletePosition(id) == 1) {
            return RespBean.ok("删除成功！");
        } else {
            return RespBean.error("删除失败！");
        }
    }

    @DeleteMapping("/")
    public RespBean deletePositionByArray(Integer[] ids) {
        if (positionService.deletePositionByArray(ids) == ids.length) {
            return RespBean.ok("批量删除成功！");
        } else {
            return RespBean.error("批量删除失败！");
        }
    }
}
