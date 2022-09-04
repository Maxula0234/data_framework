package mkhor.cleantestdata.api.dto.request.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private long ID;
    @Schema(
            name = "Номер счета",
            defaultValue = "40817810570000123456"
    )
    private String number;
    @Schema(
            name = "Владелец счета",
            defaultValue = "Иванов Иван Иванович"
    )
    private String owner;
    @Schema(
            name = "Владелец счета",
            defaultValue = "Иванов Иван Иванович"
    )
    private long owner_id;
    private boolean reserved;
    @Schema(
            name = "Остаток на счете"
    )
    private Float amount;
    @Schema(
            name = "Дата открытия счета"
    )
    private String date_create;
    @Schema(
            name = "Счетовой продукт",
            defaultValue = "Карточный продукт RUB"
    )
    private String account_product;
}
