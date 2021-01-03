package pl.edu.wszib.learningplatform.components;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.edu.wszib.learningplatform.controllers.assemblers.CourseAssembler;
import pl.edu.wszib.learningplatform.controllers.assemblers.SubCourseAssembler;
import pl.edu.wszib.learningplatform.controllers.assemblers.UserAssembler;
import pl.edu.wszib.learningplatform.controllers.dto.*;
import pl.edu.wszib.learningplatform.controllers.exceptions.BadRequestException;
import pl.edu.wszib.learningplatform.controllers.exceptions.NotFoundException;
import pl.edu.wszib.learningplatform.course.model.Course;
import pl.edu.wszib.learningplatform.subcourse.model.SubCourse;
import pl.edu.wszib.learningplatform.user.model.User;
import pl.edu.wszib.learningplatform.user.service.UserService;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static pl.edu.wszib.learningplatform.util.message.MessageTemplates.USER_NOT_FOUND_MESSAGE_TEMPLATE;

@Component
@RequiredArgsConstructor
public class UserComponent {
    private final UserService userService;
    private final UserAssembler userAssembler;
    private final CourseAssembler courseAssembler;
    private final SubCourseAssembler subCourseAssembler;
    private final PasswordEncoder passwordEncoder;


    public List<UserDto> getUsers() {
        List<User> userModels = userService.findAll();
        return userModels.stream().map(userAssembler::toDto).collect(toList());
    }

    public UserDto updateUserEmail(long userId, EmailUpdateDto email) {
        User user = userService.findById(userId).orElseThrow(
                () -> new NotFoundException(String.format(USER_NOT_FOUND_MESSAGE_TEMPLATE, userId)));

        updateUserEmail(user, email.getEmail());

        return userAssembler.toDto(userService.updateUser(user));
    }

    public UserDto updateUserPassword(long userId, PasswordUpdateDto passwordUpdateDto) {
        User user = userService.findById(userId).orElseThrow(
                () -> new NotFoundException(String.format(USER_NOT_FOUND_MESSAGE_TEMPLATE, userId)));

        updateUserPassword(user, passwordUpdateDto);

        return userAssembler.toDto(userService.updateUser(user));
    }

    private void updateUserPassword(User user, PasswordUpdateDto passwordUpdateDto){
        if(passwordUpdateDto.getNewPassword() != null){
            checkActualUserPassword(passwordUpdateDto.getCurrentPassword(), user.getPassword());
            user.setPassword(passwordEncoder.encode(passwordUpdateDto.getNewPassword()));
        }
    }

    private void checkActualUserPassword(String currentPassword, String passwordHash) {
        if (!passwordEncoder.matches(currentPassword, passwordHash)) {
            throw new BadRequestException("Invalid password.");
        }
    }

    private void updateUserEmail(User user, String email) {
        if (email != null) {
            user.setEmail(email);
        }
    }

    public List<CourseDto> getUserCourses(long userId) {
        List<Course> coursesModels = userService.findCoursesEnrolledByUserId(userId);
        return coursesModels.stream().map(courseAssembler::toDto).collect(toList());
    }

    public List<SubCourseDto> getUserSubCourses(long userId) {
        List<SubCourse> subCoursesModels = userService.findSubCoursesEnrolledByUserId(userId);
        return subCoursesModels.stream().map(subCourseAssembler::toDto).collect(toList());
    }

    public List<UserDto> getAllUsersByCourse(long courseId){
        List<User> usersModels = userService.findAllByCourseId(courseId);
        return usersModels.stream().map(userAssembler::toDto).collect(toList());
    }
}
