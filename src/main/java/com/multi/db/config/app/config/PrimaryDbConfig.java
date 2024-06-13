package com.multi.db.config.app.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.multi.db.config.app.primary.repo"
        },
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef =  "primaryTransactionManager"
)
@EnableTransactionManagement
public class PrimaryDbConfig {

    @Primary
    @Bean(name="primaryDataSource")
    @ConfigurationProperties(prefix = "spring.db1.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name="primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            @Qualifier("primaryDataSource") DataSource dataSource, EntityManagerFactoryBuilder entityManagerFactoryBuilder
            ){
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .persistenceUnit("db1")
                .packages("com.multi.db.config.app.primary.entities")
                .build();
    }

    @Primary
    @Bean(name="primaryTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory entityManagerFactory
            ){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
