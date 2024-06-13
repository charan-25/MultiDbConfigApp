package com.multi.db.config.app.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.multi.db.config.app.secondary.repo"
        },
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef =  "secondaryTransactionManager"
)
@EnableTransactionManagement
public class SecondaryDbConfig {

        @Bean(name="secondaryDataSource")
        @ConfigurationProperties(prefix = "spring.db2.datasource")
        public DataSource dataSource(){
                return DataSourceBuilder.create().build();
        }


        @Bean(name = "secondaryEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
                @Qualifier("secondaryDataSource") DataSource dataSource, EntityManagerFactoryBuilder entityManagerFactoryBuilder
                ){
                return entityManagerFactoryBuilder
                        .dataSource(dataSource)
                        .packages("com.multi.db.config.app.secondary.entities")
                        .persistenceUnit("db2")
                        .build();
        }

        @Bean(name="secondaryTransactionManager")
        public PlatformTransactionManager transactionManager(
                @Qualifier("secondaryEntityManagerFactory")EntityManagerFactory entityManagerFactory
                ){
                return new JpaTransactionManager(entityManagerFactory);
        }
}
