package com.mssm.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.mssm.goods.mapper")
public class GoodsBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodsBootApplication.class, args);
	}

}
