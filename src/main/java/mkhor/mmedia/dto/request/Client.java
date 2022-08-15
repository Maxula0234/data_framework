package mkhor.mmedia.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {
    private String fio;
    private long id;
}
