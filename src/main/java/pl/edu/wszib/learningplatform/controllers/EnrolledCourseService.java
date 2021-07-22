package pl.edu.wszib.learningplatform.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.usercourse.UserCourseRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EnrolledCourseService {

    private final UserCourseRepository userCourseRepository;

    public List<EnrolledCourseDto> getAllEnrolledCourses(User user) {
        return userCourseRepository.findByUserIdAndBought(user.getId(), true).stream()
                .map(EnrolledCourseMapper::toDto)
                .collect(toList());
    }
}
