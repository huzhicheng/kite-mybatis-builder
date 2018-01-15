package org.kite.mybatis.builder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

/**
 * BuilderApplication
 *
 * @author fengzheng
 * @date 2018/1/5
 */
@Controller
@SpringBootApplication
@MapperScan("test.dao.mapper")
public class BuilderApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BuilderApplication.class, args);
    }
}
