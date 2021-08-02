package pl.edu.wszib.learningplatform.enrolledcourse.enrolledtask;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.enrolledcourse.taskprogress.TaskProgress;
import pl.edu.wszib.learningplatform.enrolledcourse.taskprogress.TaskProgressRepository;
import pl.edu.wszib.learningplatform.user.User;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EnrolledTaskService {

    private final TaskProgressRepository taskProgressRepository;

    public List<EnrolledTaskDto> getAllCourseTasks(Long courseId, User user) {
        List<TaskProgress> taskProgressList = taskProgressRepository.findByUserCourseIdAndUserCourseUserId(courseId, user.getId());
        return taskProgressList.stream().map(EnrolledTaskMapper::toDto).collect(toList());
    }

    public void completeCourseTask(Long courseId, Long taskId, User user) {
        TaskProgress taskProgress = taskProgressRepository.findByUserCourseIdAndTaskIdAndUserCourseUserId(courseId, taskId, user.getId());
        taskProgress.setCompleted(true);
        taskProgress.setCompletionDate(LocalDate.now());
        taskProgressRepository.save(taskProgress);
    }
}
