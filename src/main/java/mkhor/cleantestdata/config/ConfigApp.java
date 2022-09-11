package mkhor.cleantestdata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ConfigApp {
    ;;;

    private static final String DB = "dataBase";

    @Bean(DB)
    public DataSource getDB(@Value("${spring.datasource.url}") String url,
                            @Value("${spring.datasource.password}") String passwd,
                            @Value("${spring.datasource.username}") String user) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(passwd);
        return dataSource;
    }
}
