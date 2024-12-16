package br.com.kenmarcos.courses_api_rs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kenmarcos.courses_api_rs.dtos.CourseDTO;
import br.com.kenmarcos.courses_api_rs.dtos.CreateCourseDTO;
import br.com.kenmarcos.courses_api_rs.entities.CourseEntity;
import br.com.kenmarcos.courses_api_rs.services.CreateCourseService;
import br.com.kenmarcos.courses_api_rs.services.FetchCoursesService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

  @Autowired
  private CreateCourseService createCourseService;

  @Autowired
  private FetchCoursesService fetchCoursesService;

  @PostMapping
  public ResponseEntity<Object> createCourse(@Valid @RequestBody CreateCourseDTO createCourseDTO) {
    try {
      var courseEntity = CourseEntity.builder()
          .name(createCourseDTO.getName())
          .category(createCourseDTO.getCategory())
          .build();

      System.out.println("courseEntity:" + courseEntity);

      CourseDTO course = createCourseService.execute(courseEntity);

      return ResponseEntity.ok().body(course);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

  @GetMapping
  public ResponseEntity<Object> fetchCourses(
      @RequestParam Optional<String> name,
      @RequestParam Optional<String> category) {
    try {
      List<CourseDTO> courses = fetchCoursesService.execute(name, category);

      return ResponseEntity.ok().body(courses);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

}
