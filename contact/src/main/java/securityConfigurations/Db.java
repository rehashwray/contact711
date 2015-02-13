package securityConfigurations;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import admin.SystemConfigurations;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Db {

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    
	    driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
	    driverManagerDataSource.setUrl(SystemConfigurations.getDbUrl());
	    driverManagerDataSource.setUsername(SystemConfigurations.getDbUser());
	    driverManagerDataSource.setPassword(SystemConfigurations.getDbPassword());
	    
	    return driverManagerDataSource;
	}

}
