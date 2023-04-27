package com.everis.d4i.tutorial.repositories;

import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<Chapter, Long> {
    List<Reward> findByTvShowId(Long tvShowId);
}
