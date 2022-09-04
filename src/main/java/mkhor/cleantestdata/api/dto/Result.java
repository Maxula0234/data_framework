package mkhor.cleantestdata.api.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class Result {
    String message;
    HttpStatus httpStatus;
}
