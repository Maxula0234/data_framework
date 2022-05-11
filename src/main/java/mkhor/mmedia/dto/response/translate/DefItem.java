package mkhor.mmedia.dto.response.translate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DefItem {
    @JsonProperty("gen")
    private String gen;
    @JsonProperty("anm")
    private String anm;
    @JsonProperty("pos")
    private String pos;
    @JsonProperty("text")
    private String text;
    @JsonProperty("tr")
    private List<TrItem> tr;
}