package com.example.osk.school.repository;

import com.example.osk.category.CategoryType;
import com.example.osk.school.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SchoolRepository
        extends JpaRepository<School, Long> {

    @Query("SELECT DISTINCT s.city FROM School s")
    Set<String> getAllCities();

    @Query("SELECT DISTINCT s FROM School s " +
            "JOIN s.categories c " +
            "WHERE (COALESCE(:cities, NULL) IS NULL OR s.city IN :cities) " +
            "AND (COALESCE(:categoryTypes, NULL) IS NULL OR c.categoryType IN :categoryTypes)")
    Set<School> findDistinctByCityInAndCategoriesCategoryTypeIn(@Param("cities") Set<String> cities, @Param("categoryTypes") Set<CategoryType> categoryTypes);

}
