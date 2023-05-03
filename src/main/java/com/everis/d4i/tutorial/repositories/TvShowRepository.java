package com.everis.d4i.tutorial.repositories;

import java.util.List;
import java.util.Optional;

import com.everis.d4i.tutorial.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.d4i.tutorial.entities.TvShow;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long> {

	List<TvShow> findByCategories(Category category);

}
