package br.com.lucassoaresdev.codepro.services;

import br.com.lucassoaresdev.codepro.dto.CreateCourseRequestDTO;
import br.com.lucassoaresdev.codepro.entities.Course;
import br.com.lucassoaresdev.codepro.enums.CourseActive;
import br.com.lucassoaresdev.codepro.exceptions.MessageBadRequestException;
import br.com.lucassoaresdev.codepro.exceptions.MessageNotFoundException;
import br.com.lucassoaresdev.codepro.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository repository;


    public void create(CreateCourseRequestDTO course) {
        Course newCourse = Course.builder()
                .name(course.name())
                .category(course.category())
                .active(CourseActive.INACTIVE)
                .build();

        this.repository.save(newCourse);
    }

    public List<Course> getAll(String name, String category) {
        List<Course> courses;

        if (!name.isBlank() && !category.isBlank())
             courses = this.repository.findAllByNameAndCategory(name, category);
        else if (!name.isBlank())
            courses = this.repository.findAllByName(name);
        else if (!category.isBlank())
            courses = this.repository.findAllByCategory(category);
        else
            courses = this.repository.findAll();

        return courses;
    }

    public void update(UUID id, CreateCourseRequestDTO updateCourse) {

        if (updateCourse.name() == null && updateCourse.category() == null)
            throw new MessageBadRequestException("Deve ser informado o nome e/ou a categoria");

        Optional<Course> course = Optional.ofNullable(this.repository.findById(id).orElseThrow(() -> new MessageNotFoundException("Curso não encontrado")));

        if (updateCourse.name() != null && !updateCourse.name().isBlank())
            course.get().setName(updateCourse.name());

        if (updateCourse.category() != null && !updateCourse.category().isBlank())
            course.get().setCategory(updateCourse.category());

        this.repository.save(course.get());
    }

    public void delete(UUID id) {
        Optional<Course> course = Optional.ofNullable(this.repository.findById(id).orElseThrow(() -> new MessageNotFoundException("Curso não encontrado")));

        this.repository.delete(course.get());
    }

    public void activate(UUID id, Boolean active) {
        Optional<Course> course = Optional.ofNullable(this.repository.findById(id).orElseThrow(() -> new MessageNotFoundException("Curso não encontrado")));

        CourseActive courseActive = active ? CourseActive.ACTIVE : CourseActive.INACTIVE;

        course.get().setActive(courseActive);

        this.repository.save(course.get());
    }
}
