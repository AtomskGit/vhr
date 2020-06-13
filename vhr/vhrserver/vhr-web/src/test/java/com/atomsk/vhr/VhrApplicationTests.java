package com.atomsk.vhr;

import com.atomsk.vhr.mapper.HrMapper;
import com.atomsk.vhr.model.Hr;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class VhrApplicationTests {

    @Autowired
    HrMapper hrMapper;

    @Test
    void contextLoads() {
        Hr hr = hrMapper.loadUserByUsername("atomsk");
        System.out.println(hr);
    }

}
