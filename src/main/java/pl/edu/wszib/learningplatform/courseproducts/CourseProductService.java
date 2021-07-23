package pl.edu.wszib.learningplatform.courseproducts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourseRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class CourseProductService {

    private final EnrolledCourseRepository enrolledCourseRepository;

    public List<CourseProductDto> getAllCourseProducts(User user) {
        return enrolledCourseRepository.findByUserId(user.getId()).stream()
                .map(CourseProductMapper::toDto)
                .collect(toList());

    }

    public List<CourseProductDto> getCourseProductsByCategory(Long courseCategoryId, User user) {
        return enrolledCourseRepository.findByCourseCategoryIdAndUserId(courseCategoryId, user.getId()).stream()
                .map(CourseProductMapper::toDto)
                .collect(toList());
    }

    public List<CourseProductDto> getCourseProductsByUrlSlug(String urlSlug, User user) {
        return enrolledCourseRepository.findByCourseCategoryUrlSlugAndUserId(urlSlug, user.getId()).stream()
                .map(CourseProductMapper::toDto)
                .collect(toList());
    }
}
