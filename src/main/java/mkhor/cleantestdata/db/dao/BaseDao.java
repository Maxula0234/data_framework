package mkhor.cleantestdata.db.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class BaseDao extends JdbcDaoSupport {
    @Value("spring.datasource.password")
    String passwd;
    @Value("spring.datasource.username")
    String user;
    @Value("spring.datasource.url")
    String url;

}
