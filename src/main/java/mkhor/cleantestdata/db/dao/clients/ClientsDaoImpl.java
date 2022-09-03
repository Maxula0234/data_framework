package mkhor.cleantestdata.db.dao.clients;

import mkhor.cleantestdata.api.dto.request.client.Client;
import mkhor.cleantestdata.api.dto.request.client.ClientMapper;
import mkhor.cleantestdata.api.exception.ClientNotBeAdd;
import mkhor.cleantestdata.api.exception.ClientNotFoundException;
import mkhor.cleantestdata.db.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    @Override
    public void addClient(Client client) {
        String query = String.format("INSERT INTO CLIENTS " +
                        "(first_name,last_name,third_name,phone_number,email,date_birth,reserve)" +
                        "VALUES ('%s','%s','%s','%s','%s',cast('%s' as date),%b)",
                client.getFirstName(),
                client.getLastName(),
                client.getThirdName(),
                client.getPhoneNumber(),
                client.getEmail(),
                client.getDateBirth(),
                false);

        try {
            getJdbcTemplate().update(query);
        } catch (DataAccessException e) {
            throw new ClientNotBeAdd("Клиент не может быть добавлен в БД");
        }
    }

    @Override
    public Client getClientById(long idClient) {
        String query = String.format("SELECT * FROM CLIENTS WHERE ID = %S", idClient);
        try {
            return getJdbcTemplate().queryForObject(query, new ClientMapper());
        } catch (DataAccessException e) {
            throw new ClientNotFoundException("Клиент с id = " + idClient + " не найден");
        }
    }

    @Override
    public void deleteClientById(long idClient) {
        Client clientById = getClientById(idClient);

        String query = "DELETE from clients where id = " + clientById.getId();
        try {
            getJdbcTemplate().update(query);
        } catch (DataAccessException e) {
            throw new ClientNotBeAdd("Не можем удалить клиента");
        }
    }

    @Override
    public Client updateClient(Client client, long idClient) {
        Client clientById = getClientById(idClient);

        if (clientById == null) {
            throw new ClientNotFoundException("Клиент с id = " + client.getId() + " не найден");
        } else {
            String query = String.format("UPDATE clients " +
                            " SET " +
                            "first_name='%s', " +
                            "last_name='%s', " +
                            "third_name='%s', " +
                            "phone_number='%s', " +
                            "email='%s', " +
                            "date_birth=cast('%s' as date), " +
                            "reserve='%b' " +
                            "WHERE id = '%s' ",
                    client.getFirstName(),
                    client.getLastName(),
                    client.getThirdName(),
                    client.getPhoneNumber(),
                    client.getEmail(),
                    client.getDateBirth(),
                    client.isReserve(),
                    idClient
            );

            try {
                getJdbcTemplate().update(query);
                return getClientById(idClient);
            } catch (DataAccessException e) {
                throw new ClientNotFoundException("Клиент не обновлен - " + e.getMessage());
            }
        }
    }
}
