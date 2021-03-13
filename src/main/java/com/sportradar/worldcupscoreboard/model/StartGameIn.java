package com.sportradar.worldcupscoreboard.model;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class StartGameIn {
    
    @NotNull(message = "Local Team is required")
    private String localTeam;

    @NotNull(message = "Away Team is required")
    private String awayTeam;

    public StartGameIn(@NotNull(message = "Local Team is required") String localTeam,
            @NotNull(message = "Away Team is required") String awayTeam) {
        this.localTeam = localTeam;
        this.awayTeam = awayTeam;
    }

    public String getLocalTeam() {
        return localTeam;
    }

    public void setLocalTeam(String localTeam) {
        this.localTeam = localTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }
}
