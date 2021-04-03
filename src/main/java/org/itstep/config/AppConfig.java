package org.itstep.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "org.itstep")
@PropertySource("classpath:db.properties")
public class AppConfig {
    @Value("${url}")
    private String url;

    @Value("${name}")
    private String username;

    @Value("${password}")
    private String password;

    @Bean("dataSource")
    public DataSource getDataSource() {
        System.out.println("url = " + url);
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
