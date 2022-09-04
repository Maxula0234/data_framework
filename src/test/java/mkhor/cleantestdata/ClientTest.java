package mkhor.cleantestdata;

import mkhor.cleantestdata.api.dto.request.client.Client;
import mkhor.cleantestdata.api.exception.ClientNotFoundException;
import mkhor.cleantestdata.api.service.clients.ClientsService;
import mkhor.cleantestdata.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class ClientTest extends BaseTest {
    @Autowired
    public ClientsService clientsService;

    @Test
    void checkAddClient() {
        String firstName = faker.name().firstName();

        Client client = Client.builder()
                .firstName(firstName)
                .lastName(faker.name().lastName())
                .thirdName(faker.name().nameWithMiddle())
                .dateBirth(DateUtils.parseDateToString(faker.date().birthday()))
                .phoneNumber(faker.phoneNumber().cellPhone())
                .email(firstName + "@mail.ru")
                .reserve(false)
                .build();

        clientsService.addNewClient(client);

        List<Client> clients = clientsService.getClients();
        Client filterClient = clients.stream()
                .filter(f -> f.getLastName().equalsIgnoreCase(client.getLastName()) &&
                        f.getFirstName().equalsIgnoreCase(client.getFirstName()) &&
                        f.getPhoneNumber().equalsIgnoreCase(client.getPhoneNumber())
                )
                .findFirst().orElseThrow(() -> new ClientNotFoundException("Клиент не найден"));

        assertAll(
                () -> assertThat(filterClient.getFirstName()).isEqualToIgnoringCase(client.getFirstName()),
                () -> assertThat(filterClient.getLastName()).isEqualToIgnoringCase(client.getLastName()),
                () -> assertThat(filterClient.getPhoneNumber()).isEqualToIgnoringCase(client.getPhoneNumber())
        );

        logger.info("Клиент добавлен в базу");
    }

}
