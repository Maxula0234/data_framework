package mkhor.cleantestdata.service.clients;

import lombok.extern.slf4j.Slf4j;
import mkhor.cleantestdata.dto.request.client.Client;
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
        Optional<Client> client = allClient.stream()
                .filter(cl -> cl.getId() == id)
                .findFirst();
        if (client.isPresent()) {
            return client.get();
        } else {
            log.info("Клиент с id {} не найден", id);
            return new Client();
        }
    }

    @Override
    public void deleteClient(long idClient) {
        Optional<Client> client = allClient.stream()
                .filter(cl -> cl.getId() == idClient)
                .findFirst();

        if (client.isPresent()) {
            allClient.remove(client.get());
            log.info("Клиента {} удален из базы", idClient);
        } else {
            log.info("Клиент с идентификатором {} не найден", idClient);
        }
    }

    @Override
    public List<Client> getClients() {
        return allClient;
    }
}
