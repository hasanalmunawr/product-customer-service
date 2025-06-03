package hasanalmunawr.dev.backend_spring.user.service;

import hasanalmunawr.dev.backend_spring.user.api.request.LoginRequest;
import hasanalmunawr.dev.backend_spring.user.api.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> registerUser(RegisterRequest request);

    ResponseEntity<?> login(LoginRequest loginRequest);

}
