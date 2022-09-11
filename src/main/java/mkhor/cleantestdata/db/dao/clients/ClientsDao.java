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

    /**
     * Добавить клиента в базу данных
     *
     * @param client сущность клиент
     */
    void addClient(Client client);

    /**
     * Найдем клиента по идентификатору
     *
     * @param idClient идентификатор клиента
     * @return Client
     */
    Client getClientById(long idClient);

    void deleteClientById(long idClient);

    Client updateClient(Client client, long idClient);
}
