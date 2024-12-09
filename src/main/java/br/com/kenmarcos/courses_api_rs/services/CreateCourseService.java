package br.com.kenmarcos.courses_api_rs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenmarcos.courses_api_rs.entities.CourseEntity;
import br.com.kenmarcos.courses_api_rs.repositories.CourseRepository;

@Service
public class CreateCourseService {

  @Autowired
  private CourseRepository courseRepository;

  public CourseEntity execute(CourseEntity courseEntity) {
    return courseRepository.save(courseEntity);
  }
}
