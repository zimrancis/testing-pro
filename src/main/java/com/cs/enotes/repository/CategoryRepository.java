package com.cs.enotes.repository;

import com.cs.enotes.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByIdAndIsDeletedFalse(Integer id);

    List<Category> findByIsActiveTrueAndIsDeletedFalse();

    List<Category> findByAndIsDeletedFalse();

}
