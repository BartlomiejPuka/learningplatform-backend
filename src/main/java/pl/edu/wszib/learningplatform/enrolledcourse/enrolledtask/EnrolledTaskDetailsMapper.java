package pl.edu.wszib.learningplatform.enrolledcourse.enrolledtask;

import lombok.experimental.UtilityClass;
import pl.edu.wszib.learningplatform.course.task.Task;
import pl.edu.wszib.learningplatform.enrolledcourse.taskprogress.TaskProgress;

@UtilityClass
public class EnrolledTaskDetailsMapper {

    public EnrolledTaskDetailsDto toDto(TaskProgress taskProgress) {
        Task task = taskProgress.getTask();
        return EnrolledTaskDetailsDto.builder()
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
