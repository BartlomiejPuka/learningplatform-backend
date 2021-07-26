package pl.edu.wszib.learningplatform.enrolledcourse.taskprogress;

import lombok.experimental.UtilityClass;
import pl.edu.wszib.learningplatform.course.task.Task;

@UtilityClass
public class TaskProgressMapper {
    public TaskProgressDto toDto(TaskProgress taskProgress) {
        Task task = taskProgress.getTask();
        return TaskProgressDto.builder()
                .id(task.getId())
                .SEID(task.getSEID())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(taskProgress.isCompleted())
                .completionDate(taskProgress.getCompletionDate())
                .build();
    }
}
