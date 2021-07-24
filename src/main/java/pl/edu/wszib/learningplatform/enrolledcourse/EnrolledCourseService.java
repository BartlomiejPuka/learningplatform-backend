package pl.edu.wszib.learningplatform.enrolledcourse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.course.CourseDetailsDto;
import pl.edu.wszib.learningplatform.course.CourseDetailsMapper;
import pl.edu.wszib.learningplatform.course.CourseDto;
import pl.edu.wszib.learningplatform.course.CourseMapper;
import pl.edu.wszib.learningplatform.user.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EnrolledCourseService {

    private final EnrolledCourseRepository enrolledCourseRepository;

    public List<CourseDto> getAllEnrolledCourses(User user) {
        return enrolledCourseRepository.findByUserIdAndBought(user.getId(), true).stream()
                .map(EnrolledCourse::getCourse)
                .map(CourseMapper::toDto)
                .collect(toList());
    }

    public CourseDetailsDto getCourseDetailsByUrlSlug(String urlSlug, User user){
        return enrolledCourseRepository.findByCourseDetailsUrlSlugAndUserId(urlSlug, user.getId())
                .map(CourseDetailsMapper::toDto)
                .orElse(null);
    }
}
