package mkhor.mmedia.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TranslateReq {
    public String lang;
    public String text;
}
