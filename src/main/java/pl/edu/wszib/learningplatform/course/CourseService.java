package pl.edu.wszib.learningplatform.course;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.util.exceptions.ConflictException;
import pl.edu.wszib.learningplatform.user.UserRepository;

import java.util.List;
import java.util.Optional;

import static pl.edu.wszib.learningplatform.util.message.MessageTemplates.USER_ALREADY_ENROLLED_TO_COURSE_TEMPLATE;

@Service
@RequiredArgsConstructor
@Log4j2
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public List<CourseEntity> findAll() { return courseRepository.findAll(); }

    public Optional<CourseEntity> findById(long courseId) { return courseRepository.findById(courseId); }

    public boolean IsUserNotEnrolledToCourse(long userId, long courseId){
        List<CourseEntity> cours = userRepository.findCoursesEnrolledById(userId);
        if(cours.stream().anyMatch(i->i.id.longValue() == courseId)){
            throw new ConflictException(String.format(USER_ALREADY_ENROLLED_TO_COURSE_TEMPLATE, userId, courseId));
        }
        return true;
    }
}
