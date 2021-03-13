package com.sportradar.worldcupscoreboard.repository;

import java.util.List;

import com.sportradar.worldcupscoreboard.entity.Scores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoresRepository extends JpaRepository<Scores, Long> {
    
    public List<Scores> findByIsFinishMatch(Boolean isFinishMatch);    
}
