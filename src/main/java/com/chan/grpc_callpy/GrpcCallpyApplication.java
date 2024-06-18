package com.chan.grpc_callpy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chan.grpc_callpy.mapper")
public class GrpcCallpyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcCallpyApplication.class, args);
    }

}
