package pl.edu.wszib.learningplatform.controllers.rest;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.learningplatform.components.UserComponent;
import pl.edu.wszib.learningplatform.controllers.dto.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserComponent userComponent;


    @ApiOperation(value = "Get all users")
    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userComponent.getUsers());
    }

    @ApiOperation(value = "Get all courses enrolled by particular user")
    @GetMapping(value = "/users/{userId}/courses", produces = "application/json")
    public ResponseEntity<List<CourseDto>> getUserCourses(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userComponent.getUserCourses(userId));
    }

    @ApiOperation(value = "Get all subcourses enrolled by particular user")
    @GetMapping(value = "/users/{userId}/subcourses", produces = "application/json")
    public ResponseEntity<List<SubCourseDto>> getUserSubCourses(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userComponent.getUserSubCourses(userId));
    }

    @ApiOperation(value = "Update user email")
    @PutMapping(value = "/users/{userId}/update-email", produces = "application/json")
    public ResponseEntity<UserDto> updateUserEmail(@PathVariable long userId, @RequestBody EmailUpdateDto email){
        return ResponseEntity.status(HttpStatus.OK).body(userComponent.updateUserEmail(userId, email));
    }

    @ApiOperation(value = "Update user password")
    @PutMapping(value = "/users/{userId}/update-password", produces = "application/json")
    public ResponseEntity<UserDto> updateUserPassword(@PathVariable long userId, @RequestBody PasswordUpdateDto passwordUpdateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userComponent.updateUserPassword(userId, passwordUpdateDto));
    }
}
