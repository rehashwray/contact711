package config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import administrator.SystemCredentials;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Db {

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    
	    driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
	    driverManagerDataSource.setUrl(SystemCredentials.getDbUrl());
	    driverManagerDataSource.setUsername(SystemCredentials.getUser());
	    driverManagerDataSource.setPassword(SystemCredentials.getPassword());
	    
	    return driverManagerDataSource;
	}

}
