package com.isa.loyalties.repository;

import com.isa.loyalties.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByNameAndType(String name, String type);
}
