package com.atomsk.vhr.mapper;

import com.atomsk.vhr.model.Employee;
import com.atomsk.vhr.model.RespPageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> getEmployeeByPage(@Param("page") Integer page,
                                     @Param("size") Integer size,
                                     @Param("emp") Employee employee,
                                     @Param("beginDateScope") Date[] beginDateScope);

    Long getTotal(@Param("emp") Employee employee,
                  @Param("beginDateScope") Date[] beginDateScope);

    Integer maxWorkID();

    Employee getEmployeeById(Integer id);

    List<Employee> getEmpSalaryByPage(@Param("page") Integer page, @Param("size") Integer size);

    Integer updateEmpSalary(@Param("eid") Integer eid, @Param("sid") Integer sid);
}