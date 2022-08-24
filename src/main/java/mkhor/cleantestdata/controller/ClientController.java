package mkhor.cleantestdata.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mkhor.cleantestdata.dto.request.client.Client;
import mkhor.cleantestdata.service.clients.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
@Tag(name = "Client", description = "Сервис для взаимодействия с тестовыми клиентами")
public class ClientController {

    @Autowired
    private ClientsService clientsService;

    @PostMapping
    @Operation(
            summary = "Добавить клиента",
            description = "Позволяет добавить клиента в базу данных"
    )
    public Client addNewClient(@RequestBody Client client) {
        return clientsService.addNewClient(client);
    }

    @GetMapping("{idClient}")
    @Operation(
            summary = "Получить клиента",
            description = "Позволяет получить информацию по клиенту по идентификатору"
    )
    public Client getClient(@PathVariable long idClient) {
        return clientsService.getClient(idClient);
    }

    @DeleteMapping("{idClient}")
    @Operation(
            summary = "Удалим клиента из базы",
            description = "Позволяет удалить клиента из базы по идентификатору"
    )
    public void deleteClient(@PathVariable long idClient) {
        clientsService.deleteClient(idClient);
    }

    @GetMapping
    @Operation(
            summary = "Получить всех клиентов",
            description = "Позволяет получить всех клиентов"
    )
    public List<Client> getAllClients() {
        return clientsService.getClients();
    }

    @PutMapping("{idClient}/reserved")
    @Operation(
            summary = "Зарезервируем клиента",
            description = "Позволяет забукировать клиентов"
    )
    public void reservedClient(@PathVariable long idClient) {
        Client client = getClient(idClient);
        client.setReserve(true);
        clientsService.updateClient(client);
    }
}
