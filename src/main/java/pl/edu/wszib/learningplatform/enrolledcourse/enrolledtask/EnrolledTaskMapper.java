package pl.edu.wszib.learningplatform.enrolledcourse.enrolledtask;

import lombok.experimental.UtilityClass;
import pl.edu.wszib.learningplatform.course.task.Task;
import pl.edu.wszib.learningplatform.enrolledcourse.taskprogress.TaskProgress;

@UtilityClass
public class EnrolledTaskMapper {

    public EnrolledTaskDto toDto(TaskProgress taskProgress) {
        Task task = taskProgress.getTask();
        return EnrolledTaskDto.builder()
                .completed(taskProgress.isCompleted())
                .completionDate(taskProgress.getCompletionDate())
                .orderId(task.getOrderId())
                .seid(task.getSeid())
                .title(task.getTitle())
                .description(task.getDescription())
                .urlSlug(task.getUrlSlug())
                .build();
    }
}
