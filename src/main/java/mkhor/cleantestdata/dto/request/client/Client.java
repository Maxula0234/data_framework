package mkhor.cleantestdata.dto.request.client;

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
    private String firstName;
    private String lastName;
    private String thirdName;
    private String phoneNumber;
    private String email;
    private LocalDate dateBirth;
    private long id;
}
