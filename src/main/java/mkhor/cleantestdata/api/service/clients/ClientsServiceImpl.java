package mkhor.cleantestdata.api.service.clients;

import lombok.extern.slf4j.Slf4j;
import mkhor.cleantestdata.api.dto.request.client.Client;
import mkhor.cleantestdata.api.service.BaseService;
import mkhor.cleantestdata.db.dao.clients.ClientsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClientsServiceImpl extends BaseService implements ClientsService {

    @Autowired
    private ClientsDao clientsDao;

    @Override
    public Client addNewClient(Client client) {
        clientsDao.addClient(client);
        return client;
    }

    @Override
    public Client getClient(long id) {
        return clientsDao.getClientById(id);
    }

    @Override
    public void deleteClient(long idClient) {
        clientsDao.deleteClientById(idClient);
        log.info("Клиент {} удален из базы", idClient);
    }

    @Override
    public List<Client> getClients() {
        return clientsDao.getAllClient();
    }

    @Override
    public void updateClient(Client client, long idClient) {
        clientsDao.updateClient(client, idClient);
    }

    @Override
    public void reserveClient(long idClient) {
        Client client = getClient(idClient);
        client.setReserve(true);
        updateClient(client, idClient);
        logger.info("Зарезервировали клиента - " + idClient);
    }
}
