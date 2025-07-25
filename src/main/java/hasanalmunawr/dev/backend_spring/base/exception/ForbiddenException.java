package hasanalmunawr.dev.backend_spring.base.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ForbiddenException extends RuntimeException {

    private final HttpStatus status;

    public ForbiddenException(String message) {
        super(message);
        this.status = HttpStatus.FORBIDDEN;
    }


}
