package mkhor.cleantestdata.service.clients;

import lombok.extern.slf4j.Slf4j;
import mkhor.cleantestdata.dto.request.client.Client;
import mkhor.cleantestdata.exception.ClientNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientsServiceImpl implements ClientsService {

    List<Client> allClient = new ArrayList<>();

    {
        allClient.add(new Client("Ivan", "Ivanovich", "Petrov", "79991112233", "i@i.ru", LocalDate.now(), 1));
        allClient.add(new Client("Petr", "Ivanov", "Popov", "79991112244", "i@i2.ru", LocalDate.now(), 2));
    }

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
        return allClient;
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
