package br.com.kenmarcos.courses_api_rs.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCourseDTO {

  @NotNull(message = "Name is required")
  private String name;

  @NotNull(message = "Category is required")
  private String category;
}
