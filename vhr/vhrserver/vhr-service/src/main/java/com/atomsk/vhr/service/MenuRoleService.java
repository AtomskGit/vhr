package com.atomsk.vhr.service;

import com.atomsk.vhr.mapper.MenuRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Atomsk
 * @github https://github.com/AtomskGit
 * @blog https://atomskgit.github.io/blog/
 * @date 2020/6/11
 */
@Service
public class MenuRoleService {
    @Autowired
    MenuRoleMapper menuRoleMapper;

    public List<Integer> getMenuIdByRoleId(Integer rid) {
        return menuRoleMapper.getMenuIdByRoleId(rid);
    }

    @Transactional
    public Boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.deleteByRoleId(rid);
        if (mids == null || mids.length == 0) {
            return true;
        }
        return menuRoleMapper.addMenuRole(rid, mids) == mids.length;
    }
}
