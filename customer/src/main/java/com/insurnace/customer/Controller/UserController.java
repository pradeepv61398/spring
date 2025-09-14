package com.insurnace.customer.Controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.insurnace.customer.DTO.LoginRequest;
import com.insurnace.customer.DTO.RegistrationRequest;
import com.insurnace.customer.EntityModel.User;
import com.insurnace.customer.Service.UserService;
@RestController
@RequestMapping("/api")
// @CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        try {
            User user = userService.registerUser(request);
            //return ResponseEntity.ok("User registered successfully");
             return ResponseEntity.ok(Map.of("message", "User registered successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(Map.of("message", "Login successful"));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body(Map.of("error", "Invalid email or password"));
    }
}

}
