package pl.edu.wszib.learningplatform.usercourse;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.course.Course;
import pl.edu.wszib.learningplatform.course.CourseRepository;
import pl.edu.wszib.learningplatform.course.lesson.Lesson;
import pl.edu.wszib.learningplatform.course.lesson.LessonRepository;
import pl.edu.wszib.learningplatform.course.task.Task;
import pl.edu.wszib.learningplatform.course.task.TaskRepository;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.usercourse.lessonprogress.LessonProgress;
import pl.edu.wszib.learningplatform.usercourse.lessonprogress.LessonProgressRepository;
import pl.edu.wszib.learningplatform.usercourse.taskprogress.TaskProgress;
import pl.edu.wszib.learningplatform.usercourse.taskprogress.TaskProgressRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCourseCreationService {

    private final UserCourseRepository userCourseRepository;
    private final CourseRepository courseRepository;
    // TODO: move those repositories?
    private final LessonRepository lessonRepository;
    private final LessonProgressRepository lessonProgressRepository;
    private final TaskRepository taskRepository;
    private final TaskProgressRepository taskProgressRepository;

    public void setupUserCourses(User user) {
        courseRepository.findAll().stream()
                .map(this::createUserCourse)
                .peek(i -> i.setUser(user))
                .forEach(userCourseRepository::save);
    }

    private UserCourse createUserCourse(Course course) {
        UserCourse userCourse = new UserCourse();
        userCourse.setCourse(course);
        return userCourseRepository.save(userCourse);
    }

    public UserCourse setPurchasedInformation(UserCourse userCourse) {
        userCourse.setPurchasedDate(LocalDate.now());
        userCourse.setBought(true);
        userCourse.setInCart(false);
        return userCourse;
    }

    public UserCourse initializeLessonsProgress(UserCourse userCourse) {
        lessonRepository.findByCourseId(userCourse.getCourse().getId()).stream()
                .map(this::createLessonProgress)
                .map(userCourse::addLessonProgress)
                .forEach(lessonProgressRepository::save);
        return userCourse;
    }

    private LessonProgress createLessonProgress(Lesson lesson) {
        LessonProgress lessonProgress = new LessonProgress();
        lessonProgress.setLesson(lesson);
        return lessonProgress;
    }

    public UserCourse initializeTasksProgress(UserCourse userCourse) {
        taskRepository.findByCourseId(userCourse.getCourse().getId()).stream()
                .map(this::createTaskProgress)
                .map(userCourse::addTaskProgress)
                .forEach(taskProgressRepository::save);
        return userCourse;
    }

    private TaskProgress createTaskProgress(Task task) {
        TaskProgress taskProgress = new TaskProgress();
        taskProgress.setTask(task);
        return taskProgress;
    }
}
