package mkhor.cleantestdata.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ClientNotBeAdd extends RuntimeException {
    public ClientNotBeAdd(String message) {
        super(message);
    }
}
