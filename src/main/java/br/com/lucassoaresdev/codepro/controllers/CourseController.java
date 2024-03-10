package br.com.lucassoaresdev.codepro.controllers;

import br.com.lucassoaresdev.codepro.dto.CreateCourseRequestDTO;
import br.com.lucassoaresdev.codepro.entities.Course;
import br.com.lucassoaresdev.codepro.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cursos")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping
    public ResponseEntity<List<Course>> getAll(@RequestParam String name, @RequestParam String category) throws Exception {
        try {
            List<Course> courses = this.service.getAll(name, category);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CreateCourseRequestDTO course) throws Exception {
        try {
            this.service.create(course);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody CreateCourseRequestDTO course) throws Exception {
        try {
            this.service.update(id, course);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) throws Exception {
        try {
            this.service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity activate(@PathVariable UUID id, @RequestBody Boolean active) throws Exception {
        try {
            this.service.activate(id, active);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
