package br.com.kenmarcos.courses_api_rs.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kenmarcos.courses_api_rs.entities.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {

}
