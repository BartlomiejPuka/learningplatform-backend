package pl.edu.wszib.learningplatform.lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {

    @Query("SELECT l FROM SubCourseEntity s " +
        "JOIN LessonEntity l ON l.subCourseId = s.id " +
        "WHERE s.id = :scid ")
    List<LessonEntity> findAllBySubCourseId(@Param("scid") Long subCourseId);
}
