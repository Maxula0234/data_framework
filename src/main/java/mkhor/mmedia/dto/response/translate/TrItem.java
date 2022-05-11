package mkhor.mmedia.dto.response.translate;

import lombok.Data;

import java.util.List;

@Data
public class TrItem {
    private String pos;
    private String text;
    private Integer fr;
    private List<SynItem> syn;
}