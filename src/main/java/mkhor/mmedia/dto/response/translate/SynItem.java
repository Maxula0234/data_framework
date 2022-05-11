package mkhor.mmedia.dto.response.translate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SynItem{
    @JsonProperty("pos")
    private String pos;
    @JsonProperty("text")
    private String text;
    @JsonProperty("fr")
    private Integer fr;
}