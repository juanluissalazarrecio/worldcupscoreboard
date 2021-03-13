package com.sportradar.worldcupscoreboard.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.sportradar.worldcupscoreboard.entity.Scores;
import com.sportradar.worldcupscoreboard.model.StartGameIn;
import com.sportradar.worldcupscoreboard.model.UpdateScoreIn;
import com.sportradar.worldcupscoreboard.service.IScoresService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

@Controller
@Validated
public class ScoresController {
    
    @Autowired
    IScoresService scoresService;

    /*
     * searchScoreLiveGames: Get a summary of games by total score. Those games with
     * the same total score will be returned ordered by the most recently added to
     * our system.
     */
    public List<Scores> searchScoreLiveGames() {
        return scoresService.searchScoreLiveGames();
    }

    /*
     * startGame: Start a game. Initial score is 0 – 0. a. Home team b. Away team
     */
    public void startGame(@NotNull(message = "Local and Away Team are required") @Valid StartGameIn startGameIn) throws Exception {
        scoresService.startGame(startGameIn);
    }

    /*
     * finishGame: Finish game. It will remove a match from the scoreboard.
     */
    public void finishGame(@NotNull(message = "Id Score is required") Long idScore) throws Exception {
        scoresService.finishGame(idScore);
    }

    /*
     * updateScore: Update score. Receiving the pair score; home team score and away
     * team score updates a game score
     */
    public void updateScore(@NotNull(message = "Id Score, Local Team Goals and Away Team Goals are required") @Valid UpdateScoreIn updateScoreIn) throws Exception {
        scoresService.updateScore(updateScoreIn);
    } 
}
