package pl.edu.wszib.learningplatform.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.learningplatform.authentication.dto.AuthenticationResponse;
import pl.edu.wszib.learningplatform.authentication.dto.LoginRequest;
import pl.edu.wszib.learningplatform.authentication.dto.RegisterRequest;
import pl.edu.wszib.learningplatform.authentication.service.AuthService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * Endpoint, który umożliwia zarejestrowanie sie użytkownika.
     * @param registerRequest - obiekt dto, który zawiera informacje z formularza rejestracyjnego
     * @return
     */
    @ApiOperation(value = "Singup new user")
    @PostMapping(value = "/signup", produces = "application/json")
    public ResponseEntity<String> signup(@Valid @RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<String>("Registration successful!", HttpStatus.OK);
    }

    @ApiOperation(value = "Verify new user account")
    @GetMapping(value = "/accountVerification/{token}", produces = "application/json")
    public ResponseEntity verify(@PathVariable String token){
        authService.verifyAccount(token);
        return ResponseEntity.status(HttpStatus.OK).body("Account activated successfully!");
    }

    @ApiOperation(value = "Login user")
    @PostMapping(value = "/login", produces = "application/json")
    public AuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
