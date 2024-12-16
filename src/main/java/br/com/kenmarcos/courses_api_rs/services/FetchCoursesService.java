package br.com.kenmarcos.courses_api_rs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenmarcos.courses_api_rs.dtos.CourseDTO;
import br.com.kenmarcos.courses_api_rs.entities.CourseEntity;
import br.com.kenmarcos.courses_api_rs.repositories.CourseRepository;

@Service
public class FetchCoursesService {

  @Autowired
  private CourseRepository courseRepository;

  public List<CourseDTO> execute(
      Optional<String> name,
      Optional<String> category) {

    List<CourseEntity> courseEntities = courseRepository.findByNameAndCategory(name, category);

    List<CourseDTO> courses = courseEntities.stream()
        .map(courseEntity -> new CourseDTO(courseEntity))
        .toList();

    return courses;
  }
}
