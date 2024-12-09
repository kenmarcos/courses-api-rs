package br.com.kenmarcos.courses_api_rs.dtos;

import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.kenmarcos.courses_api_rs.entities.CourseEntity;
import lombok.Data;

@Data
public class CourseDTO {
  private UUID id;
  private String name;
  private String category;

  @JsonProperty("isActive")
  private boolean isActive;

  public CourseDTO(CourseEntity courseEntity) {
    BeanUtils.copyProperties(courseEntity, this);
  }
}
