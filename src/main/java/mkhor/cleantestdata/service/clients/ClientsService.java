package mkhor.cleantestdata.service.clients;

import mkhor.cleantestdata.dto.request.client.Client;

import java.util.List;

public interface ClientsService {
    Client addNewClient(Client client);

    Client getClient(long id);

    void deleteClient(long idClient);

    List<Client> getClients();
}
