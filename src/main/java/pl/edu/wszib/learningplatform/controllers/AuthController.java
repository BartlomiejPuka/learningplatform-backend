package pl.edu.wszib.learningplatform.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.learningplatform.authentication.dto.AuthenticationResponse;
import pl.edu.wszib.learningplatform.authentication.dto.LoginRequest;
import pl.edu.wszib.learningplatform.authentication.dto.RegisterRequest;
import pl.edu.wszib.learningplatform.authentication.service.AuthService;
import pl.edu.wszib.learningplatform.refreshtoken.RefreshTokenRequest;
import pl.edu.wszib.learningplatform.refreshtoken.RefreshTokenService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    /**
     * Umożliwia zarejestrowanie sie użytkownika.
     * @param registerRequest - obiekt dto, który zawiera informacje z formularza rejestracyjnego
     * @return
     */
    @PostMapping(value = "/signup")
    @ResponseStatus(HttpStatus.OK)
    public String signup(@Valid @RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return "Registration successful!";
    }

    /**
     * Umożliwia zweryfikowanie użytkownika tzn. ustawienie flagi enabled = 1.
     * Tylko użytkownicy, którzy są zweryfikowani mogą sie zalogować.
     * @param token
     * @return
     */
    @GetMapping(value = "/accountVerification/{token}")
    @ResponseStatus(HttpStatus.OK)
    public String verify(@PathVariable String token){
        authService.verifyAccount(token);
        return "Account activated successfully!";
    }

    /**
     * Umożliwia zalogowanie sie użytkownika.
     * @param loginRequest
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    /**
     * Umożliwia uzyskanie refresh tokena, który przedłuża czas trwania sesji użytkownika.
     * @param refreshTokenRequest
     * @return
     */
    @PostMapping("refresh/token")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest);
    }

    /**
     * Umożliwia wylogowanie się użytkownika.
     * @param refreshTokenRequest
     * @return
     */
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public String logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return "Refresh token deleted successfully";
    }
}
