package mkhor.cleantestdata.dto.request.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private long id;
    private String pan;
    private String owner;
    private LocalDateTime dateIssued;
    private String cardProduct;
}
