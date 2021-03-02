package pl.edu.wszib.learningplatform.course;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.enrollment.EnrollmentEntity;
import pl.edu.wszib.learningplatform.enrollment.EnrollmentService;
import pl.edu.wszib.learningplatform.enrollment.EnrollmentType;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.util.exceptions.ConflictException;
import pl.edu.wszib.learningplatform.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static pl.edu.wszib.learningplatform.util.message.MessageTemplates.USER_ALREADY_ENROLLED_TO_COURSE_TEMPLATE;

@Service
@RequiredArgsConstructor
@Log4j2
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    private final UserRepository userRepository;

    private final EnrollmentService enrollmentService;

    private final UserCourseMapper userCourseMapper;

    public List<CourseDto> getCourses(){
        List<CourseEntity> courseEntities = courseRepository.findAll();
        return courseEntities.stream().map(courseMapper::toDto).collect(toList());
    }

    public List<UserCourseDto> getUserCourses() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() ->
                new RuntimeException("User not found"));
        List<CourseEntity> courses = courseRepository.findAll();
        List<EnrollmentEntity> enrollments = enrollmentService.getUserEnrollments(user.getId(), EnrollmentType.COURSE);
        return mapToUserCourseDto(courses, enrollments);
    }

    private List<UserCourseDto> mapToUserCourseDto(List<CourseEntity> courseEntities, List<EnrollmentEntity> enrollmentEntities){
        List<UserCourseDto> userCourses = new ArrayList<>();
        for(CourseEntity courseEntity: courseEntities){
            UserCourseDto userCourse = userCourseMapper.toDto(courseEntity);
            Optional<EnrollmentEntity> enrollmentEntity = enrollmentEntities.stream().filter(i->i.getCourseEntity().getId() == courseEntity.getId()).findFirst();
            userCourse.setEnrolled(enrollmentEntity.isPresent());
            userCourses.add(userCourse);
        }
        return userCourses;
    }
}
