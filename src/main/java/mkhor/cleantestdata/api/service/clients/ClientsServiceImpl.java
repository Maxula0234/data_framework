package mkhor.cleantestdata.api.service.clients;

import lombok.extern.slf4j.Slf4j;
import mkhor.cleantestdata.db.dao.clients.ClientsDao;
import mkhor.cleantestdata.api.dto.request.client.Client;
import mkhor.cleantestdata.api.exception.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientsServiceImpl implements ClientsService {

    List<Client> allClient = new ArrayList<>();

    @Autowired
    private ClientsDao clientsDao;

    @Override
    public Client addNewClient(Client client) {
        boolean add = allClient.add(client);
        return client;
    }

    @Override
    public Client getClient(long id) {
        return allClient.stream()
                .filter(cl -> cl.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ClientNotFoundException("Клиент не найден"));
    }

    @Override
    public void deleteClient(long idClient) {
        Client client = allClient.stream()
                .filter(cl -> cl.getId() == idClient)
                .findFirst()
                .orElseThrow(() -> new ClientNotFoundException("Клиент с идентификатром " + idClient + " не найден"));

        allClient.remove(client);
        log.info("Клиента {} удален из базы", idClient);
    }

    @Override
    public List<Client> getClients() {
        return clientsDao.getAllClient();
    }

    @Override
    public void updateClient(Client client) {
        Optional<Client> first = allClient.stream()
                .filter(cl -> cl.getId() == client.getId())
                .findFirst();

        if (first.isPresent()) {
            Client clFromBd = first.get();
            if (!clFromBd.getFirstName().equalsIgnoreCase(client.getFirstName()))
                clFromBd.setFirstName(client.getFirstName());
            if (!clFromBd.getDateBirth().equals(client.getDateBirth()))
                clFromBd.setDateBirth(client.getDateBirth());
            if (!clFromBd.isReserve() == client.isReserve())
                clFromBd.setReserve(client.isReserve());
        } else {
            addNewClient(client);
            log.info("Клиент добавлен");
        }
    }
}
