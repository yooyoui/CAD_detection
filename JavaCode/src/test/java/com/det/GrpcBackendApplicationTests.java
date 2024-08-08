package com.det;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class GrpcBackendApplicationTests {

    @Resource
    DataSource source;

    @Test
    void contextLoads() {

        FastAutoGenerator
                .create(new DataSourceConfig.Builder(source))
                .globalConfig(builder -> {
                    builder.author("chan")
                            .outputDir("src/main/java");
                })
                .packageConfig(builder -> builder.parent("com.chan"))
                .strategyConfig(builder -> {
                    builder.mapperBuilder()
                            .mapperAnnotation(Mapper.class)
                            .build();
                })
                .execute();
    }

}
