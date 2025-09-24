package com.insurnace.customer.Controller;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.insurnace.customer.DTO.LoginRequest;
import com.insurnace.customer.EntityModel.User;
import com.insurnace.customer.Repository.UserRepository;
import com.insurnace.customer.util.JwtUtil;

@RestController
@RequestMapping("/jwt")
@CrossOrigin(origins = "*") // Azure CORS configuration as per your requirement
public class JwtAuthController {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager; // Add this for proper authentication

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        logger.info("JWT login attempt for email: {}", request.getEmail());
        
        try {
            // Use Spring Security's AuthenticationManager for secure authentication
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
                )
            );

            // Authentication successful - generate JWT token
            String token = jwtUtil.generateToken(request.getEmail());
            
            // Get user details for response
            Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
            if (userOpt.isEmpty()) {
                logger.error("User not found after successful authentication: {}", request.getEmail());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "User data inconsistency"));
            }

            User user = userOpt.get();
            
            logger.info("JWT login successful for user: {}", request.getEmail());
            
            // Return token with user info (Azure best practice - include user context)
            return ResponseEntity.ok(Map.of(
                "token", token,
                "tokenType", "Bearer",
                "expiresIn", jwtUtil.getExpirationTime(), // Add expiration info
                "user", Map.of(
                    "id", user.getId(),
                    "email", user.getEmail(),
                    "name", user.getName()                )
            ));

        } catch (BadCredentialsException e) {
            logger.warn("Invalid credentials for email: {}", request.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                    "error", "Invalid credentials",
                    "message", "Email or password is incorrect"
                ));
                
        } catch (AuthenticationException e) {
            logger.error("Authentication failed for email: {}", request.getEmail(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                    "error", "Authentication failed",
                    "message", "Unable to authenticate user"
                ));
                
        } catch (Exception e) {
            logger.error("Unexpected error during JWT login for email: {}", request.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                    "error", "Login failed",
                    "message", "An unexpected error occurred"
                ));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> currentUser(@RequestHeader("Authorization") String authHeader) {
        logger.debug("Getting current user info from JWT token");
        
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                logger.warn("Invalid authorization header format");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid authorization header"));
            }

            String token = authHeader.substring(7);
            
            // Validate token
            if (!jwtUtil.validateToken(token)) {
                logger.warn("Invalid JWT token provided");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid or expired token"));
            }

            // Extract user email from token
            String email = jwtUtil.extractUsername(token);
            
            // Get user from database
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (userOpt.isEmpty()) {
                logger.warn("User not found for token email: {}", email);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "User not found"));
            }

            User user = userOpt.get();
            
            // Return user info without sensitive data (Azure security best practice)
            return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "email", user.getEmail(),
                "firstName", user.getName(),
                "tokenValid", true
            ));
            
        } catch (Exception e) {
            logger.error("Error getting current user info", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Failed to retrieve user information"));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String authHeader) {
        logger.info("Token refresh request");
        
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid authorization header"));
            }

            String token = authHeader.substring(7);
            
            // Validate current token
            if (!jwtUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid or expired token"));
            }

            // Extract email and generate new token
            String email = jwtUtil.extractUsername(token);
            String newToken = jwtUtil.generateToken(email);
            
            logger.info("Token refreshed for user: {}", email);
            
            return ResponseEntity.ok(Map.of(
                "token", newToken,
                "tokenType", "Bearer",
                "expiresIn", jwtUtil.getExpirationTime()
            ));
            
        } catch (Exception e) {
            logger.error("Error refreshing token", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Token refresh failed"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        logger.info("Logout request");
        
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid authorization header"));
            }

            String token = authHeader.substring(7);
            String email = jwtUtil.extractUsername(token);
            
            // In a production Azure environment, you might want to:
            // 1. Add token to a blacklist in Redis/Azure Cache
            // 2. Log the logout event for audit purposes
            // 3. Clear any cached user data
            
            logger.info("User logged out: {}", email);
            
            return ResponseEntity.ok(Map.of(
                "message", "Logout successful",
                "loggedOut", true
            ));
            
        } catch (Exception e) {
            logger.error("Error during logout", e);
            return ResponseEntity.ok(Map.of("message", "Logout completed"));
        }
    }
}