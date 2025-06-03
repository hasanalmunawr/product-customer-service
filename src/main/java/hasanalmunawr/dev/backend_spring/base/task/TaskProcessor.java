package hasanalmunawr.dev.backend_spring.base.task;

import hasanalmunawr.dev.backend_spring.base.exception.CustomApiException;
import hasanalmunawr.dev.backend_spring.base.helper.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Slf4j
@Component
public class TaskProcessor {

    @Autowired
    private ResponseHelper responseHelper;

    public TaskProcessorHandler success(String message, Object data) {
        return new TaskProcessorHandler.SuccessResponse(message, data);
    }

    public TaskProcessorHandler successWithHeader(String message, Object data, String headerName, String headerValue) {
        return new TaskProcessorHandler.SuccessResponseWithHeader(message, data, headerName, headerValue);
    }

    public TaskProcessorHandler failed(String message, Object data, HttpStatus status) {
        return new TaskProcessorHandler.ErrorResponse(message, data, status);
    }

    public <T> ResponseEntity<?> executeResponseHttp(Supplier<TaskProcessorHandler> action) {
        try {
            return action.get().handle(responseHelper);
        } catch (CustomApiException ex) {
            return responseHelper.errorResponse(ex.getStatus(), ex.getMessage(), null);
        } catch (IllegalArgumentException ex) {
            return responseHelper.errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), null);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            ex.printStackTrace(); // atau pakai log.error
            return responseHelper.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", null);
        }
    }

}
