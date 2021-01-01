package pl.edu.wszib.learningplatform.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.learningplatform.authentication.dto.AuthenticationResponse;
import pl.edu.wszib.learningplatform.authentication.dto.LoginRequest;
import pl.edu.wszib.learningplatform.authentication.dto.RegisterRequest;
import pl.edu.wszib.learningplatform.authentication.service.AuthService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<String>("Registration successful!", HttpStatus.OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity verify(@PathVariable String token){
        authService.verifyAccount(token);
        return ResponseEntity.status(HttpStatus.OK).body("Account activated successfully!");
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
