package pl.edu.wszib.learningplatform.course.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.controllers.exceptions.ConflictException;
import pl.edu.wszib.learningplatform.course.model.Course;
import pl.edu.wszib.learningplatform.course.repository.CourseRepository;
import pl.edu.wszib.learningplatform.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static pl.edu.wszib.learningplatform.util.message.MessageTemplates.USER_ALREADY_ENROLLED_TO_COURSE_TEMPLATE;

@Service
@RequiredArgsConstructor
@Log4j2
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public List<Course> findAll() { return courseRepository.findAll(); }

    public Optional<Course> findById(long courseId) { return courseRepository.findById(courseId); }

    public boolean IsUserNotEnrolledToCourse(long userId, long courseId){
        List<Course> courses = userRepository.findCoursesEnrolledById(userId);
        if(courses.stream().anyMatch(i->i.id.longValue() == courseId)){
            throw new ConflictException(String.format(USER_ALREADY_ENROLLED_TO_COURSE_TEMPLATE, userId, courseId));
        }
        return true;
    }
}
