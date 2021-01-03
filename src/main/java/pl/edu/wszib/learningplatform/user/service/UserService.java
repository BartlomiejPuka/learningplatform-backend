package pl.edu.wszib.learningplatform.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.course.model.Course;
import pl.edu.wszib.learningplatform.subcourse.Model.SubCourse;
import pl.edu.wszib.learningplatform.user.model.User;
import pl.edu.wszib.learningplatform.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(long userId) { return userRepository.findById(userId); }

    public User updateUser(User user) { return userRepository.save(user); }

    public List<Course> findCoursesEnrolledByUserId(long userId) { return userRepository.findCoursesEnrolledById(userId); }

    public List<SubCourse> findSubCoursesEnrolledByUserId(long userId) { return userRepository.findSubCoursesEnrolledById(userId);}

    public List<User> findAllByCourseId(long courseId) { return userRepository.findAllUsersByCourseId(courseId); }
}
