package com.qufu;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * componentScan当前类所在包下的扫描
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.qufu.mapper")
public class LivinginthemomentApplication {
    public static void main(String[] args) {
        log.info("服务器开始启动");
        SpringApplication.run(LivinginthemomentApplication.class, args);
        log.info("服务器启动成功");
    }
}
