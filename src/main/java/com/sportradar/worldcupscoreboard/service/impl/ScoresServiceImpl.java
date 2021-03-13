package com.sportradar.worldcupscoreboard.service.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.sportradar.worldcupscoreboard.entity.Scores;
import com.sportradar.worldcupscoreboard.entity.ScoresComparator;
import com.sportradar.worldcupscoreboard.model.StartGameIn;
import com.sportradar.worldcupscoreboard.model.UpdateScoreIn;
import com.sportradar.worldcupscoreboard.repository.ScoresRepository;
import com.sportradar.worldcupscoreboard.service.IScoresService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoresServiceImpl implements IScoresService {

    @Autowired
    private ScoresRepository scoresRepository;

    @Override
    /*
     * searchScoreLiveGames: Get a summary of games by total score. Those games with
     * the same total score will be returned ordered by the most recently added to
     * our system.
     */
    public List<Scores> searchScoreLiveGames() {

        List<Scores> scoresList = scoresRepository.findByIsFinishMatch(false);
        Collections.sort(scoresList, new ScoresComparator());
        return scoresList;
    }

    @Override
    /*
     * startGame: Start a game. Initial score is 0 – 0. a. Home team b. Away team
     * 
     * Return Exception if some of the teams are already playin other match
     */
    public void startGame(StartGameIn startGameIn) throws Exception {

        List<Scores> scoresList = searchScoreLiveGames();
        Iterator<Scores> iteratorScore = scoresList.iterator();
        while (iteratorScore.hasNext()) {
            Scores scores = iteratorScore.next();
            if (scores.getAwayTeam().equals(startGameIn.getAwayTeam())
                    || scores.getAwayTeam().equals(startGameIn.getLocalTeam())
                    || scores.getLocalTeam().equals(startGameIn.getAwayTeam())
                    || scores.getLocalTeam().equals(startGameIn.getLocalTeam())) {
                throw new Exception("Some of the teams are playing now in other match");
            }
        }

        Scores scores = new Scores(startGameIn.getLocalTeam(), 0, startGameIn.getAwayTeam(), 0, false);
        scoresRepository.save(scores);
    }

    @Override
    @Transactional
    /*
     * finishGame: Finish game. It will remove a match from the scoreboard.
     * 
     * Return Exception if the game is already finish or the id score doesn´t exist.
     */
    public void finishGame(Long idScore) throws Exception {

        try {
            Scores scores = scoresRepository.getOne(idScore);
            if (scores.getIsFinishMatch()) {
                throw new Exception("This game is already finish");
            }
            scores.setIsFinishMatch(true);
            scoresRepository.save(scores);
        } catch (EntityNotFoundException e) {
            throw new Exception("The id Score doesn´t exist.");
        }
    }

    @Override
    @Transactional
    /*
     * updateScore: Update score. Receiving the pair score; home team score and away
     * team score updates a game score
     * 
     * Return Exception if the game is already finish or the id score doesn´t exist.
     */
    public void updateScore(UpdateScoreIn updateScoreIn) throws Exception {

        try {
            Scores scores = scoresRepository.getOne(updateScoreIn.getIdScore());
            if (scores.getIsFinishMatch()) {
                throw new Exception("This game is already finish");
            }
            scores.setAwayTeamGoals(updateScoreIn.getAwayTeamGoals());
            scores.setLocalTeamGoals(updateScoreIn.getLocalTeamGoals());
            scoresRepository.save(scores);
        } catch (EntityNotFoundException e) {
            throw new Exception("The id Score doesn´t exist.");
        }
    }
}
