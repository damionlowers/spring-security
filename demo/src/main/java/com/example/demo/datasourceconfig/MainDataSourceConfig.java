package com.example.demo.datasourceconfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class MainDataSourceConfig {
    @Bean( name = "oracle19cQualifier")
    @ConfigurationProperties(prefix = "spring.datasource-oracle19c")
    public DataSource oracle19CDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oracle19CTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("oracle19cQualifier") DataSource ds){
        return new JdbcTemplate(ds);
    }
}
