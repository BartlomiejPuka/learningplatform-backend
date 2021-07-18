package pl.edu.wszib.learningplatform.courseproducts;

public interface ICourseProduct {
    Long getCourseId();
    boolean isOwned();
    String getAuthor();
    Integer getTasksCount();
    Integer getLessonsCount();
}
