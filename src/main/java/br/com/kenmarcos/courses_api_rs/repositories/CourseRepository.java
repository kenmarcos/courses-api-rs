package br.com.kenmarcos.courses_api_rs.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.kenmarcos.courses_api_rs.entities.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
  @Modifying
  @Query(nativeQuery = true, value = "SELECT * FROM courses c WHERE (:name IS NULL OR c.name = :name) AND (:category IS NULL OR c.category = :category)")
  List<CourseEntity> findByNameAndCategory(
      Optional<String> name,
      Optional<String> category);
}
