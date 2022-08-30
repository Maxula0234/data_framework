package mkhor.cleantestdata.db.dao.clients;

import mkhor.cleantestdata.api.dto.request.client.Client;

import java.util.List;

public interface ClientsDao {
    /**
     * Получить всех клиентов из базы данных
     *
     * @return список клиентов
     */
    List<Client> getAllClient();
}
