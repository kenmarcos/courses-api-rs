package br.com.kenmarcos.courses_api_rs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kenmarcos.courses_api_rs.entities.CourseEntity;
import br.com.kenmarcos.courses_api_rs.services.FetchCoursesService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

  @Autowired
  private FetchCoursesService fetchCoursesService;

  @GetMapping
  public ResponseEntity<Object> fetchCourses() {
    List<CourseEntity> courses = fetchCoursesService.execute();

    return ResponseEntity.ok().body(courses);
  }

}
