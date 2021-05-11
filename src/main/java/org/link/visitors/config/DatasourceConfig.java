package org.link.visitors.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author changmingjiang
 * @date 2020/7/31 15:35
 */
@Configuration
@MapperScan(basePackages = "org.link.visitors.core.dao", sqlSessionFactoryRef = "visitorsSqlSessionFactory")
public class DatasourceConfig {

    @Bean(name = "visitorsDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.visitors")
    public DataSource getVisitorsStoreDataSource() {
        return new HikariDataSource();
    }

    /**
     * {@link org.apache.ibatis.session.Configuration}每个数据源需要配置一份，不然导致多数据源不可用
     */
    @Bean(name = "visitorsGlobalConfig")
    @ConfigurationProperties(prefix = "mybatis.configuration.visitors")
    public org.apache.ibatis.session.Configuration getVisitorsGlobalConfig() {
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean(name = "visitorsTransactionManager")
    @Primary
    public DataSourceTransactionManager getVisitorsTransactionManager(@Qualifier("visitorsDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "visitorsSqlSessionFactory")
    @Primary
    public SqlSessionFactory getVisitorsSqlSessionFactory(@Qualifier("visitorsDataSource") DataSource dataSource) throws Exception {
        final MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }

}
