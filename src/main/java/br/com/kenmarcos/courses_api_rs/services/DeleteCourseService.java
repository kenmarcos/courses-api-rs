package br.com.kenmarcos.courses_api_rs.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenmarcos.courses_api_rs.exceptions.ResourceNotFoundException;
import br.com.kenmarcos.courses_api_rs.repositories.CourseRepository;

@Service
public class DeleteCourseService {

  @Autowired
  private CourseRepository courseRepository;
  
  public void execute(UUID courseId) {
    courseRepository.findById(courseId).orElseThrow(() -> {
      throw new ResourceNotFoundException("Course not found");
    });

    courseRepository.deleteById(courseId);
  }
}
