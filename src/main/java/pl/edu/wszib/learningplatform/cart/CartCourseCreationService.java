package pl.edu.wszib.learningplatform.cart;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.course.Course;
import pl.edu.wszib.learningplatform.course.lesson.Lesson;
import pl.edu.wszib.learningplatform.course.lesson.LessonRepository;
import pl.edu.wszib.learningplatform.course.task.Task;
import pl.edu.wszib.learningplatform.course.task.TaskRepository;
import pl.edu.wszib.learningplatform.usercourse.UserCourse;
import pl.edu.wszib.learningplatform.usercourse.UserCourseRepository;
import pl.edu.wszib.learningplatform.usercourse.lessonprogress.LessonProgress;
import pl.edu.wszib.learningplatform.usercourse.lessonprogress.LessonProgressRepository;
import pl.edu.wszib.learningplatform.usercourse.taskprogress.TaskProgress;
import pl.edu.wszib.learningplatform.usercourse.taskprogress.TaskProgressRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartCourseCreationService {

    private final UserCourseRepository userCourseRepository;
    private final LessonRepository lessonRepository;
    private final LessonProgressRepository lessonProgressRepository;
    private final TaskRepository taskRepository;
    private final TaskProgressRepository taskProgressRepository;


    public void createUserCourses(Cart cart) {
        List<CartItem> cartItems = cart.getCartItemList();
        cartItems.stream()
                .map(this::createUserCourse)
                .map(this::addLessonsProgress)
                .map(this::addTasksProgress)
                .forEach(userCourseRepository::save);
    }

    private UserCourse createUserCourse(CartItem cartItem){
        UserCourse userCourse = new UserCourse();
        Course course = cartItem.getCourse();
        userCourse.setCourse(course);
        return userCourse;
    }

    private UserCourse addLessonsProgress(UserCourse userCourse) {
        List<Lesson> lessons = lessonRepository.findByCourseId(userCourse.getCourse().getId());
        lessons.stream()
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

    private UserCourse addTasksProgress(UserCourse userCourse) {
        List<Task> tasks = taskRepository.findByCourseId(userCourse.getCourse().getId());
        tasks.stream()
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
