package pl.edu.wszib.learningplatform.course;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class CourseSpecifications {

    public static Specification<Course> likeTitle(String title){
        if(title == null){
            return null;
        }
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(criteriaBuilder.lower(root.get(Course_.TITLE)), "%" + title.toLowerCase() + "%");
        };
    }

    public static Specification<Course> createSpecification(CourseCriteria courseCriteria){
        return likeTitle(courseCriteria.getTitle());
    }
}
