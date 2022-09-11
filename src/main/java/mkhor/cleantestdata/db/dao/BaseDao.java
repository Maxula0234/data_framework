package mkhor.cleantestdata.db.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class BaseDao extends JdbcDaoSupport {
    protected final Log logger = LogFactory.getLog(this.getClass());

}
