package pl.edu.wszib.learningplatform.courseproducts;

import lombok.Getter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Immutable
@Subselect("SElECT * FROM view_course_products")
@Getter
public class ViewCourseProduct {

    @Id
    private Long id;

    private Long courseId;

    private String author;

    private String description;

    private String iconUrl;

    private BigDecimal price;

    private String title;

    private String category;

    private Long userId;

    private boolean owned;
}
