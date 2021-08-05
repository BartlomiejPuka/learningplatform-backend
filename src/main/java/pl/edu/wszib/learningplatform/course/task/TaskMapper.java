package pl.edu.wszib.learningplatform.course.task;


import lombok.experimental.UtilityClass;

@UtilityClass
public class TaskMapper {

    public TaskDto toDto(Task task){
        return TaskDto.builder()
                .id(task.getId())
                .description(task.getDescription())
                .orderId(task.getOrderId())
                .SEID(task.getSeid())
                .title(task.getTitle())
                .build();
    }
}
