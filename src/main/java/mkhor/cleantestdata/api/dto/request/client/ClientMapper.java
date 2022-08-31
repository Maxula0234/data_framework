package mkhor.cleantestdata.api.dto.request.client;

import mkhor.cleantestdata.utils.ResultSetUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Client client = new Client();

        if (ResultSetUtils.containsColumn(resultSet, "id")) {
            client.setId(resultSet.getLong("id"));
        }
        if (ResultSetUtils.containsColumn(resultSet, "first_name")) {
            client.setFirstName(resultSet.getString("first_name"));
        }
        if (ResultSetUtils.containsColumn(resultSet, "last_name")) {
            client.setLastName(resultSet.getString("last_name"));
        }
        if (ResultSetUtils.containsColumn(resultSet, "third_name")) {
            client.setThirdName(resultSet.getString("third_name"));
        }
        if (ResultSetUtils.containsColumn(resultSet, "phone_number")) {
            client.setPhoneNumber(resultSet.getString("phone_number"));
        }
        if (ResultSetUtils.containsColumn(resultSet, "email")) {
            client.setEmail(resultSet.getString("email"));
        }
        if (ResultSetUtils.containsColumn(resultSet, "reserve")) {
            client.setReserve(resultSet.getBoolean("reserve"));
        }
        if (ResultSetUtils.containsColumn(resultSet, "date_birth")) {
            client.setDateBirth(resultSet.getString("date_birth"));
        }

        return client;
    }
}
