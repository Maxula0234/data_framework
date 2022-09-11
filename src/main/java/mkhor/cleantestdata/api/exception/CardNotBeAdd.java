package mkhor.cleantestdata.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardNotBeAdd extends RuntimeException {
    public CardNotBeAdd(String message) {
        super(message);
    }
}
