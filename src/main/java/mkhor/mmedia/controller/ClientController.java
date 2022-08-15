package mkhor.mmedia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mkhor.mmedia.dto.request.Client;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
@Tag(name = "Client", description = "Сервис для взаимодействия с тестовыми клиентами")
public class ClientController {

    @PostMapping
    @Operation(
            summary = "Добавить клиента",
            description = "Позволяет добавить клиента в базу данных"
    )
    public Client addNewClient(@RequestBody Client client) {
        return null;
    }

    @GetMapping("{idClient}")
    public Client getClient(@PathVariable long idClient) {
        return null;
    }
}
