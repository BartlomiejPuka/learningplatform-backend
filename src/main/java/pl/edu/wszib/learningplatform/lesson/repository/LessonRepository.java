package pl.edu.wszib.learningplatform.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.learningplatform.lesson.model.Lesson;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("SELECT l FROM SubCourse s " +
        "JOIN Lesson l ON l.subCourseId = s.id " +
        "WHERE s.id = :scid ")
    List<Lesson> findAllBySubCourseId(@Param("scid") Long subCourseId);
}
