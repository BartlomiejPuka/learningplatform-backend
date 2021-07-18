package pl.edu.wszib.learningplatform.courseproducts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.user.User;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class CourseProductService {

    private final CourseProductRepository courseProductRepository;
    private final ViewCourseProductRepository viewCourseProductRepository;

    public List<CourseProductDto> getAllCourseProducts(User user) {
        List<ViewCourseProduct> viewCourseProducts = viewCourseProductRepository.findByUserId(user.getId());
        return new ArrayList<>();
        //        List<ICourseProduct> courses = courseProductRepository.getCourseProducts(user.getId());
//        List<CourseProductDto> courseProductDtos = courses.stream().map(CourseProductMapper::toDto).collect(toList());
//        return courseProductDtos;
    }
}
