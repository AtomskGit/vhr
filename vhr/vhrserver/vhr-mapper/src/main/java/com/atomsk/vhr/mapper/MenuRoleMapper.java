package com.atomsk.vhr.mapper;

import com.atomsk.vhr.model.MenuRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    MenuRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);

    List<Integer> getMenuIdByRoleId(Integer rid);

    void deleteByRoleId(Integer rid);

    Integer addMenuRole(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}