package pl.edu.wszib.learningplatform.courseproducts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.usercourse.UserCourseRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class CourseProductService {

    private final UserCourseRepository userCourseRepository;

    public List<CourseProductDto> getAllCourseProducts(User user) {
        return userCourseRepository.findByUserId(user.getId()).stream()
                .map(CourseProductMapper::toDto)
                .collect(toList());

    }
}
