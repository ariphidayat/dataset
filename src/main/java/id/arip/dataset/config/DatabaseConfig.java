package id.arip.dataset.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Configuration
public class DatabaseConfig {

    @Resource
    private Environment env;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.driverClassName("org.sqlite.JDBC");
        builder.url("jdbc:sqlite:dataset.db");
        return builder.build();
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("id.arip.dataset.model");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean;
    }

    @PostConstruct
    public void initialize() {
        try {
            Connection conn = dataSource().getConnection();
            Statement statement = conn.createStatement();

            statement.execute("DROP TABLE IF EXISTS user");

            statement.executeUpdate(
                    "CREATE TABLE user(" +
                            "username VARCHAR(25) NOT NULL," +
                            "password VARCHAR(50) NOT NULL," +
                            "role VARCHAR(20) NOT NULL)"
            );

            statement.executeUpdate("INSERT INTO user(username, password, role) " +
                    "VALUES('admin', '" + passwordEncoder.encode("admin")+ "', 'ADMIN')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        return properties;
    }
}
