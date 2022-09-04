package mkhor.cleantestdata.api.dto.request.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private long ID;
    private String number;
    private String owner;
    private long owner_id;
    private boolean reserved;
    private BigDecimal amount;
    private String date_issued;
    private String account_product;
}
