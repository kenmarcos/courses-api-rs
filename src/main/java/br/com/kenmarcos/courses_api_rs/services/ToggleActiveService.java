package br.com.kenmarcos.courses_api_rs.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenmarcos.courses_api_rs.dtos.CourseDTO;
import br.com.kenmarcos.courses_api_rs.entities.CourseEntity;
import br.com.kenmarcos.courses_api_rs.exceptions.ResourceNotFoundException;
import br.com.kenmarcos.courses_api_rs.repositories.CourseRepository;

@Service
public class ToggleActiveService {
  @Autowired
  private CourseRepository courseRepository;
  
  public CourseDTO execute (UUID courseId) {
    CourseEntity courseEntity = courseRepository.findById(courseId).orElseThrow(() -> {
      throw new ResourceNotFoundException("Course not found");
    });

    courseEntity.setActive(!courseEntity.isActive());

    courseRepository.save(courseEntity);

    CourseDTO courseDto = new CourseDTO(courseEntity);

    return courseDto;
  }
}
