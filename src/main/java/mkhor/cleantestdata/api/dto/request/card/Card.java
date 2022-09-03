package mkhor.cleantestdata.api.dto.request.card;

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
public class Card {
    private long id;
    @Schema(
            description = "Номер карты",
            defaultValue = "4444111155556666"
    )
    private String pan;
    @Schema(
            name = "Владелец карты",
            defaultValue = "Иванов Иван Иванович"
    )
    private String owner;
    private String dateIssued;
    @Schema(
            name = "Карточный продукт",
            description = "Продукт к которому выпущена карта",
            defaultValue = "МИР Виртуальная"
    )
    private String cardProduct;
}
