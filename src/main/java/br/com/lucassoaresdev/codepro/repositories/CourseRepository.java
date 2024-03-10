package br.com.lucassoaresdev.codepro.repositories;

import br.com.lucassoaresdev.codepro.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    List<Course> findAllByName(String name);

    List<Course> findAllByCategory(String category);

    List<Course> findAllByNameAndCategory(String name, String category);
}
