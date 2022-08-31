package mkhor.cleantestdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ConfigApp {

    private static final String DB = "dataBase";

    @Bean(DB)
    public DataSource getDB() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/cleantestdata");
        dataSource.setUsername("root");
        dataSource.setPassword("123");
        return dataSource;
    }
}
