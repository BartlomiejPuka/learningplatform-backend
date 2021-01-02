package pl.edu.wszib.learningplatform.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.learningplatform.components.UserComponent;
import pl.edu.wszib.learningplatform.controllers.dto.CourseDto;
import pl.edu.wszib.learningplatform.controllers.dto.EmailUpdateDto;
import pl.edu.wszib.learningplatform.controllers.dto.PasswordUpdateDto;
import pl.edu.wszib.learningplatform.controllers.dto.UserDto;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserComponent userComponent;


    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userComponent.getUsers());
    }

    @GetMapping(value = "/users/{userId}/courses")
    public ResponseEntity<List<CourseDto>> getUserCourses(@PathVariable long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userComponent.getUserCourses(userId));
    }

    @PutMapping(value = "/users/{userId}/update-email", produces = "application/json")
    public ResponseEntity<UserDto> updateUserEmail(@PathVariable long userId, @RequestBody EmailUpdateDto email){
        return ResponseEntity.status(HttpStatus.OK).body(userComponent.updateUserEmail(userId, email));
    }

    @PutMapping(value = "/users/{userId}/update-password", produces = "application/json")
    public ResponseEntity<UserDto> updateUserPassword(@PathVariable long userId, @RequestBody PasswordUpdateDto passwordUpdateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userComponent.updateUserPassword(userId, passwordUpdateDto));
    }
}
