package com.bd.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// 代表这是一个springboot应用。
@SpringBootApplication(scanBasePackages = {"com.bd.file"})
@EnableDiscoveryClient
public class BdFileServiceApplication {

    /**
     * 主启动类所在包的子包都会被springboot自动扫描。也可通过scanBasePackages属性手动扫描。
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(BdFileServiceApplication.class, args);
    }

}
