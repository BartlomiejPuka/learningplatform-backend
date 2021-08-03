package pl.edu.wszib.learningplatform.courseproducts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourseRepository;
import pl.edu.wszib.learningplatform.user.User;

@Service
@RequiredArgsConstructor
public class CourseProductDetailsService {

    private final EnrolledCourseRepository enrolledCourseRepository;

    public CourseProductDetailsDto getCourseProductDetailsByUrlSlug(String urlSlug, User user) {
            return enrolledCourseRepository.findByCourseDetailsUrlSlugAndUserId(urlSlug, user.getId())
                    .map(CourseProductDetailsMapper::toDto)
                    .orElse(null);
    }
}
