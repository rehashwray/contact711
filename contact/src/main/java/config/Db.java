package config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import db.DbCredentials;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Db {

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    
	    driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
	    driverManagerDataSource.setUrl(DbCredentials.getUrl());
	    driverManagerDataSource.setUsername(DbCredentials.getUser());
	    driverManagerDataSource.setPassword(DbCredentials.getPassword());
	    
	    return driverManagerDataSource;
	}

}
