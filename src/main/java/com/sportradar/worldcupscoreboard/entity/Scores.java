package com.sportradar.worldcupscoreboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SCORES")
public class Scores {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SCORE")
    private Long idScore;

    @Column(name = "LOCAL_TEAM")
    private String localTeam;

    @Column(name = "LOCAL_TEAM_GOALS")
    private Integer localTeamGoals;

    @Column(name = "AWAY_TEAM")
    private String awayTeam;

    @Column(name = "AWAY_TEAM_GOALS")
    private Integer awayTeamGoals;

    @Column(name = "IS_FINISH_MATCH")
    private Boolean isFinishMatch;

    public Scores(Long idScore, String localTeam, Integer localTeamGoals, String awayTeam, Integer awayTeamGoals,
            Boolean isFinishMatch) {
        this.idScore = idScore;
        this.localTeam = localTeam;
        this.localTeamGoals = localTeamGoals;
        this.awayTeam = awayTeam;
        this.awayTeamGoals = awayTeamGoals;
        this.isFinishMatch = isFinishMatch;
    }

    public Scores(String localTeam, Integer localTeamGoals, String awayTeam, Integer awayTeamGoals,
            Boolean isFinishMatch) {
        this.localTeam = localTeam;
        this.localTeamGoals = localTeamGoals;
        this.awayTeam = awayTeam;
        this.awayTeamGoals = awayTeamGoals;
        this.isFinishMatch = isFinishMatch;
    }

    public Scores() {
    }

    public Long getIdScore() {
        return idScore;
    }

    public void setIdScore(Long idScore) {
        this.idScore = idScore;
    }

    public String getLocalTeam() {
        return localTeam;
    }

    public void setLocalTeam(String localTeam) {
        this.localTeam = localTeam;
    }

    public Integer getLocalTeamGoals() {
        return localTeamGoals;
    }

    public void setLocalTeamGoals(Integer localTeamGoals) {
        this.localTeamGoals = localTeamGoals;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Integer getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(Integer awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public Boolean getIsFinishMatch() {
        return isFinishMatch;
    }

    public void setIsFinishMatch(Boolean isFinishMatch) {
        this.isFinishMatch = isFinishMatch;
    }
}
