package br.com.kenmarcos.courses_api_rs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenmarcos.courses_api_rs.entities.CourseEntity;
import br.com.kenmarcos.courses_api_rs.repositories.CourseRepository;

@Service
public class FetchCoursesService {

  @Autowired
  private CourseRepository courseRepository;

  public List<CourseEntity> execute() {
    List<CourseEntity> courses = courseRepository.findAll();

    return courses;
  }
}
