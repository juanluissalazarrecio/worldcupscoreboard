package com.sportradar.worldcupscoreboard.service;

import java.util.List;

import com.sportradar.worldcupscoreboard.entity.Scores;
import com.sportradar.worldcupscoreboard.model.StartGameIn;
import com.sportradar.worldcupscoreboard.model.UpdateScoreIn;

public interface IScoresService {

    public List<Scores> searchScoreLiveGames();
    public void startGame(StartGameIn startGameIn) throws Exception;
    public void finishGame(Long idScore) throws Exception;
    public void updateScore(UpdateScoreIn updateScoreIn) throws Exception;
}
