package hasanalmunawr.dev.backend_spring.user.service.impl;

import hasanalmunawr.dev.backend_spring.base.constants.ResponseMessage;
import hasanalmunawr.dev.backend_spring.base.exception.CustomApiException;
import hasanalmunawr.dev.backend_spring.base.helper.RoleHelper;
import hasanalmunawr.dev.backend_spring.base.repository.GeneralRepository;
import hasanalmunawr.dev.backend_spring.base.task.TaskProcessor;
import hasanalmunawr.dev.backend_spring.base.task.TaskProcessorHandler;
import hasanalmunawr.dev.backend_spring.user.api.request.LoginRequest;
import hasanalmunawr.dev.backend_spring.user.api.request.RegisterRequest;
import hasanalmunawr.dev.backend_spring.user.api.response.UserResponse;
import hasanalmunawr.dev.backend_spring.user.model.UserModel;
import hasanalmunawr.dev.backend_spring.user.service.UserService;
import hasanalmunawr.dev.backend_spring.web.config.JwtConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final TaskProcessor taskProcessor;

    private final GeneralRepository generalRepository;

    private final JwtConfig jwtConfig;
//    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
//    @Autowired
//    private SecurityHelper securityHelper;

    @Autowired
    private RoleHelper roleHelper;


    @Override
    public ResponseEntity<?> registerUser(RegisterRequest request) {
        return taskProcessor.executeResponseHttp(() -> {

            Optional<UserModel> user = generalRepository.getUserRepository().findByEmail(request.getEmail());

            if (user.isPresent()) {
                throw new CustomApiException(ResponseMessage.User.USER_ALREADY_REGISTERED, HttpStatus.BAD_REQUEST);
            }

            UserModel userModel = new UserModel()
                    .setUsername(request.getUsername())
                    .setEmail(request.getEmail())
                    .setPassword(passwordEncoder.encode(request.getPassword())) // di sini
                    .setRole(roleHelper.convertToRole(request.getRole()))
                    .setPhone(request.getPhone())
                    .setEnabled(true);

                generalRepository.getUserRepository().save(userModel);

            return taskProcessor.success(ResponseMessage.User.USER_REGISTERED, UserResponse.fromModel(userModel));
        });
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        return taskProcessor.executeResponseHttp(() -> {
            // 1. Authentikasi user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            // 2. Ambil detail user setelah sukses login
            UserModel user = (UserModel) authentication.getPrincipal();

            // 3. Ambil roles user
            List<String> roles = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            // 4. Tambahkan informasi ke claim JWT jika perlu
            Map<String, Object> claims = new HashMap<>();
            claims.put("roles", roles);
            claims.put("username", user.getUsername());

            // 5. Generate token
            String token = jwtConfig.generateToken(claims, user);

            // 6. Bungkus response
            Map<String, Object> result = new HashMap<>();
            result.put("token", token);
            result.put("tokenType", "Bearer");
            result.put("expiresIn", jwtConfig.getJwtExpiration());
            result.put("user", Map.of(
                    "username", user.getUsername(),
                    "roles", roles
            ));

            return taskProcessor.success(ResponseMessage.User.USER_LOGIN_SUCCESS, result);
        });
    }
}
