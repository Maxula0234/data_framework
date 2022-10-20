package mkhor.cleantestdata.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mkhor.cleantestdata.api.dto.request.client.Client;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${application.health}")
@Tag(name = "Health", description = "Проверяем работоспособность сервиса")
public class ServiceController {

    @GetMapping()
    @Operation(
            summary = "Добавить клиента",
            description = "Позволяет добавить клиента в базу данных"
    )
    @ResponseStatus(code = HttpStatus.OK)
    public String addNewClient() {
        return "Сервис доступен";
    }
}
