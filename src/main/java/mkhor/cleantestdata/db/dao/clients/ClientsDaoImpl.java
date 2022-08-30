package mkhor.cleantestdata.db.dao.clients;

import mkhor.cleantestdata.api.dto.request.client.Client;
import mkhor.cleantestdata.api.dto.request.client.ClientMapper;
import mkhor.cleantestdata.db.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class ClientsDaoImpl extends BaseDao implements ClientsDao {

    @Autowired
    public ClientsDaoImpl(DataSource dataBase) {
        setDataSource(dataBase);
    }

    @Override
    public List<Client> getAllClient() {
        String query = "select * from clients";
        return getJdbcTemplate().query(query, new ClientMapper());
    }
}
