package mkhor.mmedia.service.clients;

import mkhor.mmedia.dto.request.Client;

public interface ClientsService {
    Client addNewClient(Client client);

    Client getClient(long id);
}
