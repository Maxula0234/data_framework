package mkhor.cleantestdata.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountIsNotDelete extends RuntimeException {
    public AccountIsNotDelete(String message) {
        super(message);
    }
}
