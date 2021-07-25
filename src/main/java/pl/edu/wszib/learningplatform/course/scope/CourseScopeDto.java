package pl.edu.wszib.learningplatform.course.scope;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CourseScopeDto {
    Long id;
    String scope;
}
