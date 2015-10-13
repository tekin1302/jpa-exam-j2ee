package ro.tekin.jpa.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    private static final String BD_DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private static final String BD_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String BD_USERNAME = "admin";
    private static final String BD_PASSWORD = "password";
    private static final Object HIBERNATE_DIALECT = "org.hibernate.dialect.PostgreSQLDialect";
    private static final String PERSISTENCE_UNIT_NAME = "exam";

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = null;
        Connection connection = null;

        try {
            dataSource = getConfiguredDataSouce();
            connection = dataSource.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();

            System.out.println("DB user: " + metaData.getUserName());
            System.out.println("DB url: " + metaData.getURL());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataSource;
    }

    protected DataSource getConfiguredDataSouce() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(BD_DRIVER_CLASS_NAME);
        dataSource.setUrl(BD_URL);
        dataSource.setUsername(BD_USERNAME);
        dataSource.setPassword(BD_PASSWORD);

        return dataSource;
    }

    @Bean(name = "examJpaProperties")
    public Properties getJpaProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        return jpaProperties;
    }


    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       @Qualifier("examJpaProperties") Properties properties) {

        LocalContainerEntityManagerFactoryBean factoryBean = createEntityManagerFactoryBean(dataSource, PERSISTENCE_UNIT_NAME);
        factoryBean.setJpaProperties(properties);

        return factoryBean;
    }

    @Bean
    @Autowired
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

    protected LocalContainerEntityManagerFactoryBean createEntityManagerFactoryBean(DataSource dataSource, String persistenceUnitName) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(dataSource);
        factoryBean.setPersistenceUnitName(persistenceUnitName);
        factoryBean.setJpaDialect(new HibernateJpaDialect());
        factoryBean.setPersistenceProviderClass(HibernatePersistence.class);

        return factoryBean;
    }
}
