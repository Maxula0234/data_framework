package mkhor.cleantestdata.api.dto.request.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Schema(
            description = "Фамилия клиента",
            defaultValue = "Иван"
    )
    private String firstName;
    @Schema(defaultValue = "Иванов")
    private String lastName;
    @Schema(defaultValue = "Иванович")
    private String thirdName;
    @Schema(defaultValue = "79991112233")
    private String phoneNumber;
    @Schema(defaultValue = "m@test.ru")
    private String email;
    private String dateBirth;
    @Schema(defaultValue = "false")
    private boolean reserve;
    private long id;

    public Client(String firstName, String lastName, String thirdName, String phoneNumber, String email, String dateBirth, long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.thirdName = thirdName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateBirth = dateBirth;
        this.id = id;
    }
}
