package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Categories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Categories entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    @Query("SELECT categories FROM Categories categories")
    public List<Categories> findAllCategories();

    @Query("SELECT categories FROM Categories categories WHERE categories.code= ?1")
    public Optional<Categories> findByCode(String code);

    @Query("SELECT categories FROM Categories categories WHERE categories.libelle= ?1")
    public Optional<Categories> findByLibelle(String libelle);
}
