package mkhor.cleantestdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiError {
    String message;
    HttpStatus httpStatus;
    LocalDateTime timeStamp;
}
