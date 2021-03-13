package com.sportradar.worldcupscoreboard.entity;

import java.util.Comparator;

public class ScoresComparator implements Comparator<Scores> {

      public int compare(Scores scoresA, Scores scoresB) {

            try {

                  Integer totalScoresB = (scoresB.getAwayTeamGoals() + scoresB.getLocalTeamGoals());
                  Integer totalScoresA = (scoresA.getAwayTeamGoals() + scoresA.getLocalTeamGoals());
                  int resultCompare = totalScoresB.compareTo(totalScoresA);

                  if (resultCompare == 0) {
                        resultCompare = scoresB.getIdScore().compareTo(scoresA.getIdScore());
                  }

                  return resultCompare;

            } catch (Exception e) {
                  e.printStackTrace();
            }

            return 0;
      }
}
