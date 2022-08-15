package mkhor.mmedia.service.clients;

import mkhor.mmedia.dto.request.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientsServiceImpl implements ClientsService {

    List<Client> allClient = new ArrayList<>();

    @Override
    public Client addNewClient(Client client) {
        boolean add = allClient.add(client);
        if (add) {
            return client;
        } else return null;
    }

    @Override
    public Client getClient(long id) {
        return allClient.stream()
                .filter(cl -> cl.getId() == id)
                .findFirst().get();
    }
}
