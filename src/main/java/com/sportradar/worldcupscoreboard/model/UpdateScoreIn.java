package com.sportradar.worldcupscoreboard.model;

import javax.validation.constraints.NotNull;

public class UpdateScoreIn {
    
    @NotNull(message = "Id Score is required")
    private Long idScore;

    @NotNull(message = "Local Team Goals is required")
    private Integer localTeamGoals;

    @NotNull(message = "Away Team Goals is required")
    private Integer awayTeamGoals;

    public UpdateScoreIn(@NotNull(message = "Id Score is required") Long idScore,
            @NotNull(message = "Local Team Goals is required") Integer localTeamGoals,
            @NotNull(message = "Away Team Goals is required") Integer awayTeamGoals) {
        this.idScore = idScore;
        this.localTeamGoals = localTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
    }
    
    public UpdateScoreIn() {
    }

    public Long getIdScore() {
        return idScore;
    }

    public void setIdScore(Long idScore) {
        this.idScore = idScore;
    }

    public Integer getLocalTeamGoals() {
        return localTeamGoals;
    }

    public void setLocalTeamGoals(Integer localTeamGoals) {
        this.localTeamGoals = localTeamGoals;
    }

    public Integer getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(Integer awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }
}
