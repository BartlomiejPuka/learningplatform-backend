package pl.edu.wszib.learningplatform.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.learningplatform.authentication.dto.AuthenticationResponse;
import pl.edu.wszib.learningplatform.authentication.dto.LoginRequest;
import pl.edu.wszib.learningplatform.authentication.dto.RegisterRequest;
import pl.edu.wszib.learningplatform.authentication.service.AuthService;
import pl.edu.wszib.learningplatform.authentication.refreshtoken.RefreshTokenRequest;
import pl.edu.wszib.learningplatform.authentication.refreshtoken.RefreshTokenService;
import pl.edu.wszib.learningplatform.cart.CartItemDto;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "All endpoints that manage authentication/authorization.")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping(value = "/signup")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Sign up to the website.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = String.class))})
            })
    public String signup(@Valid @RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return "Registration successful!";
    }

    @GetMapping(value = "/accountVerification/{token}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Verify your account after signing up.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = String.class))})
    })
    public String verify(@PathVariable String token){
        authService.verifyAccount(token);
        return "Account activated successfully!";
    }

/*    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }*/

    @PostMapping("refresh/token")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get new valid refresh and jwt token.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ResponseEntity.class))})
    })
    public ResponseEntity<?> refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Logout from the website.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = String.class))})
    })
    public String logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return "Refresh token deleted successfully";
    }
}
