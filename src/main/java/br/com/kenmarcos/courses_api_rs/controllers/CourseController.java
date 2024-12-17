package br.com.kenmarcos.courses_api_rs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kenmarcos.courses_api_rs.dtos.CourseDTO;
import br.com.kenmarcos.courses_api_rs.dtos.CreateCourseDTO;
import br.com.kenmarcos.courses_api_rs.dtos.UpdateCourseDTO;
import br.com.kenmarcos.courses_api_rs.entities.CourseEntity;
import br.com.kenmarcos.courses_api_rs.exceptions.ResourceNotFoundException;
import br.com.kenmarcos.courses_api_rs.services.CreateCourseService;
import br.com.kenmarcos.courses_api_rs.services.DeleteCourseService;
import br.com.kenmarcos.courses_api_rs.services.FetchCoursesService;
import br.com.kenmarcos.courses_api_rs.services.ToggleActiveService;
import br.com.kenmarcos.courses_api_rs.services.UpdateCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses", description = "Endpoints de cursos")
public class CourseController {

  @Autowired
  private CreateCourseService createCourseService;

  @Autowired
  private FetchCoursesService fetchCoursesService;

  @Autowired
  private DeleteCourseService deleteCourseService;

  @Autowired
  private UpdateCourseService updateCourseService;

  @Autowired
  private ToggleActiveService toggleActiveService;

  @PostMapping
  @Operation(summary = "Cria um novo curso")
  @ApiResponses({
    @ApiResponse(responseCode = "201", content = {
      @Content(schema = @Schema(implementation = CourseDTO.class))
    }),
  })
  public ResponseEntity<Object> createCourse(@Valid @RequestBody CreateCourseDTO createCourseDTO) {
    try {
      var courseEntity = CourseEntity.builder()
          .name(createCourseDTO.getName())
          .category(createCourseDTO.getCategory())
          .build();

      System.out.println("courseEntity:" + courseEntity);

      CourseDTO course = createCourseService.execute(courseEntity);

      return ResponseEntity.status(HttpStatus.CREATED).body(course);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

  @GetMapping
  @Operation(summary = "Lista todos os cursos")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(array = @ArraySchema(schema = @Schema(implementation = CourseDTO.class)))
    })
  })
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

  @PutMapping("/{courseId}")
  @Operation(summary = "Atualiza um curso")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(schema = @Schema(implementation = CourseDTO.class))
    }),
    @ApiResponse(responseCode = "404", description = "Curso nao encontrado"),
  })
  public ResponseEntity<Object> updateCourse(
    @PathVariable UUID courseId,
    @Valid @RequestBody UpdateCourseDTO createCourseDTO) {
      try {
        CourseDTO courseUpdated = updateCourseService.execute(courseId, createCourseDTO);

        return ResponseEntity.ok().body(courseUpdated);
      } catch (ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); 
      } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    }

  @DeleteMapping("/{courseId}")
  @Operation(summary = "Deleta um curso")
  @ApiResponses({
    @ApiResponse(responseCode = "204"),
    @ApiResponse(responseCode = "404", description = "Curso nao encontrado"),
  })
  public ResponseEntity<Object> deleteCourse(@PathVariable UUID courseId) {
    try {
      deleteCourseService.execute(courseId);

      return ResponseEntity.noContent().build();
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PatchMapping("/{courseId}/active")
  @Operation(summary = "Ativa ou desativa um curso")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(schema = @Schema(implementation = CourseDTO.class))
    }),
    @ApiResponse(responseCode = "404", description = "Curso nao encontrado"),
  })
  public ResponseEntity<Object> toggleActive(@PathVariable UUID courseId) {
    try {
      CourseDTO course = toggleActiveService.execute(courseId);

      return ResponseEntity.ok().body(course);
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
