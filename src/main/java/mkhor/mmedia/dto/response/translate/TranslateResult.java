package mkhor.mmedia.dto.response.translate;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TranslateResult{
    @JsonProperty("head")
    private Head head;
    @JsonProperty("def")
    private List<DefItem> def;
}