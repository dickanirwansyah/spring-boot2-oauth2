package com.springboot.oauth2.springboot2oauth2.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import javax.sql.DataSource;

@Configuration
public class ConfigJdbc {


    @Value("${spring.datasource.url}")
    private String datasourceURI;

    @Value("${spring.datasource.username}")
    private String datasourceUsr;

    @Value("${spring.datasource.password}")
    private String datasourcePwd;

    @Value("${spring.datasource.driver-class-name}")
    private String datasourceDriver;

    @Bean
    public DataSource dataSource(){
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(datasourceURI);
        dataSource.setUsername(datasourceUsr);
        dataSource.setPassword(datasourcePwd);
        dataSource.setDriverClassName(datasourceDriver);
        return dataSource;
    }
}
