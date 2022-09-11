package mkhor.cleantestdata;

import mkhor.cleantestdata.api.dto.request.client.Client;
import mkhor.cleantestdata.api.exception.ClientNotFoundException;
import mkhor.cleantestdata.api.service.clients.ClientsService;
import mkhor.cleantestdata.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class ClientTest extends BaseTest {

    @Autowired
    public ClientsService clientsService;

    @Test
    void testAddClient() {
        addClient();
    }

    private Client addClient() {
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
        return filterClient;
    }

    @Test
    void testDeleteClient() {
        Client client = addClient();
        clientsService.deleteClient(client.getId());
        List<Client> clients = clientsService.getClients();
        Optional<Client> first = clients.stream()
                .filter(cl -> cl.getId() == client.getId())
                .findFirst();
        assertThat(first).as("Клиент найден").isNotPresent();
    }

    @Test
    void testGetClients() {
        List<Client> clients = clientsService.getClients();
        assertThat(clients).isNotEmpty();
        assertThat(clients).allSatisfy(c -> {
                    assertAll(
                            () -> assertThat(c.getPhoneNumber()).isNotNull(),
                            () -> assertThat(c.getId()).isNotNull(),
                            () -> assertThat(c.getFirstName()).isNotNull(),
                            () -> assertThat(c.getLastName()).isNotNull(),
                            () -> assertThat(c.getThirdName()).isNotNull(),
                            () -> assertThat(c.getDateBirth()).isNotNull(),
                            () -> assertThat(c.getDateBirth()).isNotNull()
                    );
                }
        );
        logger.info("Получили клиентов, клиенты корректны");
    }

    @Test
    void getClient() {
        List<Client> clients = clientsService.getClients();
        Collections.shuffle(clients);

        Client client = clientsService.getClient(clients.get(0).getId());
        assertAll(
                () -> assertThat(client.getPhoneNumber()).isNotNull(),
                () -> assertThat(client.getId()).isNotNull(),
                () -> assertThat(client.getFirstName()).isNotNull(),
                () -> assertThat(client.getLastName()).isNotNull(),
                () -> assertThat(client.getThirdName()).isNotNull(),
                () -> assertThat(client.getDateBirth()).isNotNull(),
                () -> assertThat(client.getDateBirth()).isNotNull()
        );
        logger.info("Получен клиент " + client.getId() + ", клиент корректен");
    }
}
