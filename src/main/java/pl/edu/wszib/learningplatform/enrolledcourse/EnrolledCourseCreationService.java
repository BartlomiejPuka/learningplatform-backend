package pl.edu.wszib.learningplatform.enrolledcourse;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.course.Course;
import pl.edu.wszib.learningplatform.course.CourseRepository;
import pl.edu.wszib.learningplatform.course.lesson.Lesson;
import pl.edu.wszib.learningplatform.course.lesson.LessonRepository;
import pl.edu.wszib.learningplatform.course.task.Task;
import pl.edu.wszib.learningplatform.course.task.TaskRepository;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.enrolledcourse.lessonprogress.LessonProgress;
import pl.edu.wszib.learningplatform.enrolledcourse.lessonprogress.LessonProgressRepository;
import pl.edu.wszib.learningplatform.enrolledcourse.taskprogress.TaskProgress;
import pl.edu.wszib.learningplatform.enrolledcourse.taskprogress.TaskProgressRepository;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EnrolledCourseCreationService {

    private final EnrolledCourseRepository enrolledCourseRepository;
    private final CourseRepository courseRepository;
    // TODO: move those repositories?
    private final LessonRepository lessonRepository;
    private final LessonProgressRepository lessonProgressRepository;
    private final TaskRepository taskRepository;
    private final TaskProgressRepository taskProgressRepository;

    public void setupUserCourses(User user) {
        List<EnrolledCourse> userCoursesList = courseRepository.findAll().stream()
                .map(this::createUserCourse)
                .peek(i -> i.setUser(user))
                .collect(toList());
        enrolledCourseRepository.saveAll(userCoursesList);
    }

    private EnrolledCourse createUserCourse(Course course) {
        EnrolledCourse userCourse = new EnrolledCourse();
        userCourse.setCourse(course);
        return enrolledCourseRepository.save(userCourse);
    }

    public EnrolledCourse setPurchasedInformation(EnrolledCourse userCourse) {
        userCourse.setPurchasedDate(LocalDate.now());
        userCourse.setBought(true);
        userCourse.setInCart(false);
        return userCourse;
    }

    public EnrolledCourse initializeLessonsProgress(EnrolledCourse userCourse) {
        List<LessonProgress> lessonProgressList = lessonRepository.findByCourseId(userCourse.getCourse().getId()).stream()
                .map(this::createLessonProgress)
                .map(userCourse::addLessonProgress)
                .collect(toList());
        lessonProgressRepository.saveAll(lessonProgressList);
        return userCourse;
    }

    private LessonProgress createLessonProgress(Lesson lesson) {
        LessonProgress lessonProgress = new LessonProgress();
        lessonProgress.setLesson(lesson);
        return lessonProgress;
    }

    public EnrolledCourse initializeTasksProgress(EnrolledCourse userCourse) {
        List<TaskProgress> taskProgressList = taskRepository.findByCourseId(userCourse.getCourse().getId()).stream()
                .map(this::createTaskProgress)
                .map(userCourse::addTaskProgress)
                .collect(toList());
        taskProgressRepository.saveAll(taskProgressList);
        return userCourse;
    }

    private TaskProgress createTaskProgress(Task task) {
        TaskProgress taskProgress = new TaskProgress();
        taskProgress.setTask(task);
        return taskProgress;
    }
}
