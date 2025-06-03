package hasanalmunawr.dev.backend_spring.user.controller;

import hasanalmunawr.dev.backend_spring.base.constants.Endpoint;
import hasanalmunawr.dev.backend_spring.base.task.TaskProcessor;
import hasanalmunawr.dev.backend_spring.user.api.request.LoginRequest;
import hasanalmunawr.dev.backend_spring.user.api.request.RegisterRequest;
import hasanalmunawr.dev.backend_spring.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoint.Base.AUTHENTICATION)
@Tag(name = "Authentication Controller", description = "API for managing auth-related operations.")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping(Endpoint.Auth.REGISTER)
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return userService.registerUser(registerRequest);
    }

    @PostMapping(Endpoint.Auth.LOGIN)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }


}
